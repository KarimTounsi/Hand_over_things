package pl.coderslab.charity.user.controllers;


import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;


@RestController
@AllArgsConstructor
public class EditUserProfileViewController {

    UserService userService;

    @RequestMapping("/user/edit-profile")
    public ModelAndView viewInstitutions(Model model, Principal principal) {
        User userByEmail = userService.getUserByEmail(principal.getName());

        model.addAttribute("id",userByEmail.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/editUserProfile");
        return modelAndView;
    }


}
