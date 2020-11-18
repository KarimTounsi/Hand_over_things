package pl.coderslab.charity.donation.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.user.entity.User;


import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import java.util.Random;


@DisplayName("Donation Service Specification")
class DonationServiceTest {

    DonationService donationService;

    // Zestaw zależności do zamokowania
    private DonationRepository donationRepository;
    private InstitutionService institutionService;
    private CategoryService categoryService;


    @BeforeEach
    void setUp() {
        donationRepository = Mockito.mock(DonationRepository.class);
        institutionService = Mockito.mock(InstitutionService.class);
        categoryService = Mockito.mock(CategoryService.class);
        donationService = new DonationServiceImpl(donationRepository, institutionService, categoryService);
    }

    @DisplayName("Saving donation")
    @Nested
    class SavingDonation {

        DonationDTO donationToSave;
        User user;

        @BeforeEach
        void setUp() {
            donationToSave = DonationDTO.builder()
                    .city("city")
                    .street("street")
                    .zipCode("00-000")
                    .quantity(5)
                    .phoneNumber("+00 000-000-000")
                    .pickUpDate(LocalDate.now().plusDays(4))
                    .pickUpTime(LocalTime.now())
                    .pickUpComment("test-comment")
                    .categories(List.of(new Category(1L, "category"), new Category(2L, "category2")))
                    .institution(new Institution(1L, "new institution", "description", true))
                    .build();

            user = new User(1L, "email", "password", "role", true, "token");
        }

        Random random = new Random();

        @DisplayName(" - should save any donation")
        @Test
        public void test1() {
            Mockito.when(donationRepository.save(ArgumentMatchers.notNull())).thenReturn(null);
            Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(new Category(random.nextLong(), "category"));
            Mockito.when(institutionService.getInstitutionById(ArgumentMatchers.anyLong())).thenReturn(new Institution(random.nextLong(), "new institution", "description", true));
            donationService.saveDonation(donationToSave, user);

            Mockito.verify(donationRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.notNull());
        }

        @DisplayName(" - should ask for all categories")
        @Test
        public void test2() {
            Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(null);

            Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(new Category(random.nextLong(), "category"));

            Mockito.when(institutionService.getInstitutionById(ArgumentMatchers.anyLong())).thenReturn(new Institution(random.nextLong(), "new institution", "description", true));
            donationService.saveDonation(donationToSave, user);

            Mockito.verify(categoryService, Mockito.times(donationToSave.getCategories().size()*2))
                    .getCategoryById(ArgumentMatchers.anyLong());
        }

        @DisplayName(" - should ask for institution")
        @Test
        public void test3() {
            Mockito.when(institutionService.getInstitutionById(donationToSave.getInstitution().getId())).thenReturn(null);

            Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(new Category(random.nextLong(), "category"));

            Mockito.when(institutionService.getInstitutionById(ArgumentMatchers.anyLong())).thenReturn(new Institution(random.nextLong(), "new institution", "description", true));

            donationService.saveDonation(donationToSave, user);

            Mockito.verify(institutionService, Mockito.atLeastOnce())
                    .getInstitutionById(donationToSave.getInstitution().getId());
        }

        @DisplayName(" - should save donation with all provided data")
        @Test
        public void test4() {
            ArgumentCaptor<Donation> donationCaptor = ArgumentCaptor.forClass(Donation.class);
            Mockito.when(donationRepository.save(donationCaptor.capture())).thenAnswer(invocation -> {
                Donation argument = invocation.getArgument(0, Donation.class); // argument, który otrzymała metoda save
                argument.setId(999L);
                return argument;
            });
            Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(new Category(random.nextLong(), "category"));

            Mockito.when(institutionService.getInstitutionById(ArgumentMatchers.anyLong())).thenReturn(new Institution(random.nextLong(), "new institution", "description", true));

            donationService.saveDonation(donationToSave, user);

            Donation savedDonation = donationCaptor.getValue();
            Assertions.assertThat(savedDonation).isNotNull()
                    .hasFieldOrPropertyWithValue("city", donationToSave.getCity())
                    .extracting(Donation::getCategories)
                    .asInstanceOf(InstanceOfAssertFactories.list(Category.class))
                    .hasSameSizeAs(donationToSave.getCategories());
        }

        @DisplayName("things go wrong")
        @Nested
        class ThingsGoWrong {

            @DisplayName(" - when any category id doesn't exist throws exception")
            @Test
            public void test1() {
                Mockito.when(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(null);

                Assertions.assertThatThrownBy(() -> donationService.saveDonation(donationToSave, user))
                        .isInstanceOf(RuntimeException.class);
            }

            @DisplayName(" - when institution id doesn't exist throws exception")
            @Test
            public void test2() {
                Mockito.when(institutionService.getInstitutionById(ArgumentMatchers.anyLong())).thenReturn(null);

                Assertions.assertThatThrownBy(() -> donationService.saveDonation(donationToSave, user))
                        .isInstanceOf(RuntimeException.class);
            }
        }

    }


}