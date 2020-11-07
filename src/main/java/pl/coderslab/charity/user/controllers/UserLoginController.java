package pl.coderslab.charity.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.user.service.UserService;


@Controller
@AllArgsConstructor
public class UserLoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String userLoginForm() {
        return "user/login";
    }


}


