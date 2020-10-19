package pl.coderslab.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {

    InstitutionService institutionService;




    @RequestMapping("/")
    public String homeAction(Model model){

        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.getAll();
    }



}
