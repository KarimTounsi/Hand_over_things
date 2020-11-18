package pl.coderslab.charity.donation.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.entity.Donation;

@Component
@RequiredArgsConstructor
public class DonationAssembler {



    private final ModelMapper modelMapper;


    public Donation getDonation(DonationDTO donationDTO) {
        Donation donation = modelMapper.map(donationDTO, Donation.class);
        return donation;


    }


}
