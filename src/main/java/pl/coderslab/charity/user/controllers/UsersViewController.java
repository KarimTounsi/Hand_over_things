package pl.coderslab.charity.user.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@AllArgsConstructor
public class UsersViewController {


    @RequestMapping("/admin/users/view")
    public ModelAndView viewInstitutions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/users");
        return modelAndView;
    }


}
