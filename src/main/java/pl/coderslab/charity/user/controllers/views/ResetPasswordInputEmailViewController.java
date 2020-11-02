package pl.coderslab.charity.user.controllers.views;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class ResetPasswordInputEmailViewController {

    UserService userService;

    @RequestMapping("/reset")
    public String viewResetPasswordInputEmail(Model model, Principal principal) {


        return "user/resetPasswordInputEmail";
    }


}
