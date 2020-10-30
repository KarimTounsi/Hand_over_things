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

    List<Donation> getAllByUserOrderByCreatedDesc(User user);
    List<Donation> getAllByUserOrderByReceiveStatusAsc(User user);
    List<Donation> getAllByUserOrderByReceiveStatusDesc(User user);

    List<Donation> getAllByUserOrderByPickUpDesc(User user);
    List<Donation> getAllByUserOrderByPickUpAsc(User user);

    List<Donation> getAllByUserOrderByCreatedAsc(User user);


}
