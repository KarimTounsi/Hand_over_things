package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.donation.DonationDTO;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserRegisterController {

UserService userService;

    @GetMapping("/register")
    public String userForm(Model model ){

        model.addAttribute("userDTO" , new UserDTO());

        return "user/register";
    }


    @PostMapping("/register")
    public String saveUser(@Valid UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user/register";
        }

        userService.saveUser(userDTO);

        return "redirect:/";
    }

}
