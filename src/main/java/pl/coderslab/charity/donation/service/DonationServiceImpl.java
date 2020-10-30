package pl.coderslab.charity.donation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.donation.repository.DonationRepository;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class DonationServiceImpl implements DonationService {

    DonationRepository donationRepository;
    UserRepository userRepository;

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
    public void saveDonation(DonationDTO donationDTO, Principal principal) {

        Donation donation = new Donation();
        donation.setQuantity(donationDTO.getQuantity());
        donation.setUser(userRepository.findUserByEmail(principal.getName()));
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


}
