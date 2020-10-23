package pl.coderslab.charity.institution;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RestController
@AllArgsConstructor
public class InstitutionsViewController {


    @RequestMapping("/institutions/view")
    public ModelAndView viewInstitutions(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("institutions/institutions");
        return modelAndView;
    }


}
