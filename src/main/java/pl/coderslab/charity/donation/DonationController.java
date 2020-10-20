package pl.coderslab.charity.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class DonationController {

    CategoryService categoryService;
    InstitutionService institutionService;
    DonationService donationService;


    @GetMapping("/donation/form")
    public String donationForm(Model model ){

        model.addAttribute("donationDto" , new DonationDTO());

        return "form";
    }

    @PostMapping("/donation/form")
    public String saveDonation(DonationDTO donationDTO){

        donationService.saveDonation(donationDTO);

        return "form";
    }


    @ModelAttribute("categories")
    public List<Category> AllCategories() {
        return categoryService.getAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> AllInstitutions() {
        return institutionService.getAll();
    }

}
