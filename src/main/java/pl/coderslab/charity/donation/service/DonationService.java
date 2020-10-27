package pl.coderslab.charity.donation.service;

import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;
import pl.coderslab.charity.user.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface DonationService {

    List<Donation> getAll();
    Integer getSumOfQuantity();

    Integer getListSize();

    void saveDonation(DonationDTO donationDTO, Principal principal);

    List<Donation> getAllByUser(User user);

    Optional<Donation> getById(Long id);
    
}
