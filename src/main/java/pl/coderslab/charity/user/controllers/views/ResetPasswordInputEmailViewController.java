package pl.coderslab.charity.user.controllers.views;


import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.email.service.EmailService;
import pl.coderslab.charity.user.DTOS.EmailDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class ResetPasswordInputEmailViewController {

    final private UserService userService;
    final private EmailService emailService;


    @GetMapping("/reset")
    public String viewResetPasswordInputEmail(Model model) {

        model.addAttribute("emailDTO", new EmailDTO());
        return "user/resetPasswordInputEmail";
    }

    @PostMapping("/reset")
    public String CheckPasswordInputEmail(@Valid EmailDTO emailDTO , Model model, Principal principal, BindingResult bindingResult) throws MessagingException, TemplateException, NoSuchAlgorithmException, IOException {
        if (bindingResult.hasErrors()){
            return "user/resetPasswordInputEmail";
        }

        if (userService.getUserByEmail(emailDTO.getEmail()) != null) {
            emailService.sendResetPasswordEmail(emailDTO.getEmail());

        }
        return "redirect:/";
    }


    @GetMapping("/reset/{id}/{token}")
    public String checkToken(@PathVariable Long id ,@PathVariable String token,  Model model) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getToken().equals(token)) {
                return "redirect:/resetPassword/" + id + "/" + token+"";
            }
        }
        return null;
    }

}
