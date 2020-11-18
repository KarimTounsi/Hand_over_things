package pl.coderslab.charity.donation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.assembler.DonationAssembler;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.institution.repository.InstitutionRepository;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    //    private final DonationAssembler donationAssembler;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    @Override
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    @Override
    public Integer getSumOfQuantity() {
        return donationRepository.findAll().stream().mapToInt(Donation::getQuantity).sum();
    }

    @Override
    public Integer getListSize() {
        return donationRepository.findAll().size();
    }

    @Override
    public void saveDonation(DonationDTO donationDTO, User user) {

        Donation donation = new Donation();
        donation.setQuantity(donationDTO.getQuantity());
        donation.setUser(user);


        List<Category> categories = donationDTO.getCategories().stream().peek(category -> {
            if (categoryService.getCategoryById(category.getId()) != null) {
                categoryService.getCategoryById(category.getId());
            } else {
                throw new RuntimeException();
            }
        }).collect(Collectors.toList());

        donation.setCategories(categories);

        if (institutionService.getInstitutionById(donationDTO.getInstitution().getId()) !=null){
            donation.setInstitution(institutionService.getInstitutionById(donationDTO.getInstitution().getId()));
        } else{
            throw new RuntimeException();
        }

        donation.setStreet(donationDTO.getStreet());
        donation.setCity(donationDTO.getCity());
        donation.setZipCode(donationDTO.getZipCode());
        donation.setPickUpDate(donationDTO.getPickUpDate());
        donation.setPickUpTime(donationDTO.getPickUpTime());
        donation.setPickUpComment(donationDTO.getPickUpComment());
        donation.setPhoneNumber(donationDTO.getPhoneNumber());


//        Donation donation = donationAssembler.getDonation(donationDTO);
//        donation.setUser(user);

        donationRepository.save(donation);
        log.info("donation saved: " + donation);
    }

    @Override
    public List<Donation> getAllByUser(User user) {
        return donationRepository.findAllByUser(user);
    }

    @Override
    public Optional<Donation> getById(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public List<Donation> getAllByUserOrderByCreatedDesc(User user) {
        return donationRepository.findAllByUserOrderByCreatedDesc(user);
    }

    @Override
    public List<Donation> getAllByUserOrderByReceiveStatusAsc(User user) {
        return donationRepository.findAllByUserOrderByReceiveStatusAsc(user);
    }

    @Override
    public List<Donation> getAllByUserOrderByReceiveStatusDesc(User user) {
        return donationRepository.findAllByUserOrderByReceiveStatusDesc(user);
    }

    @Override
    public List<Donation> getAllByUserOrderByPickUpDesc(User user) {
        return donationRepository.findAllByUserAndReceiveStatusTrueOrderByPickUpDesc(user);
    }

    @Override
    public List<Donation> getAllByUserOrderByPickUpAsc(User user) {
        return donationRepository.findAllByUserAndReceiveStatusTrueOrderByPickUpAsc(user);
    }

    @Override
    public List<Donation> getAllByUserOrderByCreatedAsc(User user) {
        return donationRepository.findAllByUserOrderByCreatedAsc(user);
    }

    @Override
    public Donation getByIdAndUser(long id, User user) {
        return donationRepository.findByIdAndUser(id, user);
    }

    @Override
    public void updateDonation(Donation donation) {
        donation.setReceiveStatus(true);
        donationRepository.save(donation);
        log.info("donation updated: " + donation);
    }


}
