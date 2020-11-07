package pl.coderslab.charity.user.controllers;

import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.email.service.EmailService;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserService userService;
    private final EmailService emailService;


    @GetMapping("/register")
    public String userForm(Model model) {

        model.addAttribute("userDTO", new UserDTO());

        return "user/register";
    }


    @PostMapping("/register")
    public String saveUser(@Valid UserDTO userDTO, BindingResult bindingResult) throws IOException, NoSuchAlgorithmException, TemplateException, MessagingException {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        User user = userService.saveUser(userDTO);
        emailService.sendRegisterEmail(user);

        log.info("User saved: " + user.getId());

        return "redirect:/";
    }


    @GetMapping("/activation/{id}/{token}")
    public String userForm(@PathVariable Long id, @PathVariable String token) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if (user.getToken().equals(token)){
                userService.activateUser(user);
            }
        }
        return "redirect:/";
    }

}
