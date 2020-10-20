package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class DonationServiceImpl implements DonationService {

    DonationRepository donationRepository;

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
    public void saveDonation(DonationDTO donationDTO) {
        Donation donation = new Donation();
        donation.setQuantity(donationDTO.getQuantity());
        donation.setCategories(donationDTO.getCategories());
        donation.setInstitution(donationDTO.getInstitution());
        donation.setStreet(donationDTO.getStreet());
        donation.setCity(donationDTO.getCity());
        donation.setZipCode(donationDTO.getZipCode());
        donation.setPickUpDate(donationDTO.getPickUpDate());
        donation.setPickUpTime(donationDTO.getPickUpTime());
        donation.setPickUpComment(donationDTO.getPickUpComment());
        donation.setPhoneNumber(donationDTO.getPhoneNumber());

        donationRepository.save(donation);
        log.info("donation saved: " + donation);
    }


}
