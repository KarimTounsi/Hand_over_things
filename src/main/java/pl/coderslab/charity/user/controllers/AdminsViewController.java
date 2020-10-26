package pl.coderslab.charity.user.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;




@RestController
@AllArgsConstructor
public class AdminsViewController {


    @RequestMapping("/admin/admins/view")
    public ModelAndView viewInstitutions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/admins");
        return modelAndView;
    }


}
