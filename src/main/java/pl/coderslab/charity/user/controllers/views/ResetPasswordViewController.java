package pl.coderslab.charity.user.controllers.views;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.charity.user.DTOS.PasswordDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
@AllArgsConstructor
@SessionAttributes({"id", "token"})
public class ResetPasswordViewController {

    UserService userService;

    @GetMapping("/resetPassword/{id}/{token}")
    public String viewResetPassword(@PathVariable Long id, @PathVariable String token, Model model, Principal principal) {
        model.addAttribute("id", id);
        model.addAttribute("token", token);
        model.addAttribute("PasswordDTO", new PasswordDTO());

        return "user/resetPassword";
    }

    @PostMapping("/resetPassword/{id}/{token}")
    public String ResetPassword(@Valid PasswordDTO passwordDTO,BindingResult bindingResult, Model model, Principal principal ) {
        if (bindingResult.hasErrors()) {
            return "user/resetPassword";
        }

        Optional<User> userOptional = userService.getUserById(passwordDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getToken().equals(passwordDTO.getToken())) {
                user.setPassword(passwordDTO.getPassword());
                userService.updateUserPassword(user);
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
