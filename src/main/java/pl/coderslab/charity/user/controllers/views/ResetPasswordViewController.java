package pl.coderslab.charity.user.controllers.views;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class ResetPasswordViewController {

    UserService userService;

    @RequestMapping("/resetPassword//{id}/{token}")
    public String viewResetPasswordInputEmail(@PathVariable Long id, @PathVariable String token, Model model, Principal principal) {
        model.addAttribute("id", id);
        model.addAttribute("token" , token);

        return "user/resetPassword";
    }


}
