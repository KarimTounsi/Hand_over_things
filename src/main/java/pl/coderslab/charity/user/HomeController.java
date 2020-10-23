package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;

    private final DonationService donationService;

    private final UserService userService;

    @RequestMapping("/")
    public String homeAction(Principal principal) {
        if (principal != null) {
            User user = userService.getUserByEmail(principal.getName());
            if (user.getRole().equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            }

        }
        return "user/home";
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
