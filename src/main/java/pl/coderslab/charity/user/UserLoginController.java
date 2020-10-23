package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
public class UserLoginController {

    private final  UserService userService;

    @GetMapping("/login")
    public String userLoginForm() {
        return "user/login";
    }


}


