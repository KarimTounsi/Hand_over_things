package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;


@Controller
@AllArgsConstructor
public class AdminHomeController {

    InstitutionService institutionService;

    DonationService donationService;

    @RequestMapping("/admin")
    public String homeAction(Model model){
        return "user/adminHome";
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
