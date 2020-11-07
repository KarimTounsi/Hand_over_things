package pl.coderslab.charity.donation.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DonationConfirmController {


    @GetMapping("/user/donation/confirm")
    public String donationForm( ){

        return "donation/confirm";
    }



}
