package pl.coderslab.charity.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.service.InstitutionService;


@Controller
@AllArgsConstructor
public class AdminHomeController {

    private final InstitutionService institutionService;

    private final  DonationService donationService;

    @RequestMapping("/admin")
    public String homeAction(Model model){
        return "user/adminHome";
    }


}
