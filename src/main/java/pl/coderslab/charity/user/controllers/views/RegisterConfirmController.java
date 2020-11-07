package pl.coderslab.charity.user.controllers.views;

import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class RegisterConfirmController {




    @GetMapping("/register/confirm")
    public String RegisterConfirm(Model model) {

        return "user/registerConfirm";
    }



}
