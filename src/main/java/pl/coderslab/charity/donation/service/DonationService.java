package pl.coderslab.charity.donation.service;

import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;

import java.util.List;

public interface DonationService {

    List<Donation> getAll();
    Integer getSumOfQuantity();

    Integer getListSize();

    void saveDonation(DonationDTO donationDTO);
    
}
