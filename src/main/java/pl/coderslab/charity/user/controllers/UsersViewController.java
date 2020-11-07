package pl.coderslab.charity.user.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class UsersViewController {


    @RequestMapping("/admin/users/view")
    public String viewUsers() {
        return "user/users";
    }


}
