package pl.coderslab.charity.user.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
@AllArgsConstructor
public class AdminsViewController {

    @RequestMapping("/admin/admins/view")
    public String viewInstitutions() {
        return "user/admins";
    }

}
