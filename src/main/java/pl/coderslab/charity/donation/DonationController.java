package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;

import java.util.List;

@Controller
public class DonationController {

    @RequestMapping("/donation/form")
    public String donationForm(Model model){

        return "form";
    }


}
