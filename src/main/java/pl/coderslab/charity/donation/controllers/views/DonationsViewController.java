package pl.coderslab.charity.donation.controllers.views;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.service.InstitutionService;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
public class DonationsViewController {

    private final UserService userService;
    private final InstitutionService institutionService;

    private final DonationService donationService;

    @RequestMapping("/user/donations")
    public String viewInstitutions(Model model, Principal principal) {
        return "user/donations";
    }

    @ModelAttribute("institutions")
    public List<Institution> AllInstitutions() {
        return institutionService.getAll();
    }

    @ModelAttribute("sumOfQuantity")
    public Integer SumOfQuantity() {
        return donationService.getSumOfQuantity();
    }


    @ModelAttribute("numberOfDonations")
    public Integer numberOfDonations() {
        return donationService.getListSize();
    }


}
