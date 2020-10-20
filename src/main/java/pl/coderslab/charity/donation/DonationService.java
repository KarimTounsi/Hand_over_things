package pl.coderslab.charity.donation;

import java.util.List;

public interface DonationService {

    List<Donation> getAll();
    Integer getSumOfQuantity();

    Integer getListSize();

    void saveDonation(DonationDTO donationDTO);
    
}
