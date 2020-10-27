package pl.coderslab.charity.donation.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.category.entity.Category;
import pl.coderslab.charity.category.service.CategoryService;
import pl.coderslab.charity.donation.DTOS.DonationDTO;
import pl.coderslab.charity.donation.service.DonationService;
import pl.coderslab.charity.institution.entity.Institution;
import pl.coderslab.charity.institution.service.InstitutionService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class DonationFormController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;


    @GetMapping("/user/donation/form")
    public String donationForm(Model model) {

        model.addAttribute("donationDto", new DonationDTO());

        return "donation/form";
    }

    @PostMapping("/user/donation/form")
    public String saveDonation(DonationDTO donationDTO, Principal principal) {

        donationService.saveDonation(donationDTO, principal);

        return "redirect:/user/donation/confirm";
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
