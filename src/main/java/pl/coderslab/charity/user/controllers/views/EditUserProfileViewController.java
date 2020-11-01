package pl.coderslab.charity.user.controllers.views;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class EditUserProfileViewController {

    UserService userService;

    @RequestMapping("/user/edit-profile")
    public String viewEditProfile(Model model, Principal principal) {
        User userByEmail = userService.getUserByEmail(principal.getName());
        model.addAttribute("id",userByEmail.getId());
        return "user/editUserProfile";
    }


}
