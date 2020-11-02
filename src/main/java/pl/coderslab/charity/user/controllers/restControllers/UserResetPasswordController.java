package pl.coderslab.charity.user.controllers.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.email.service.EmailService;
import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.DTOS.EmailDTO;
import pl.coderslab.charity.user.DTOS.PasswordDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reset")
@RequiredArgsConstructor
@Slf4j
public class UserResetPasswordController {

    private final UserService userService;
    private final EmailService emailService;


    @PutMapping
    public ResponseEntity ResetPasswordEmailInput(@Valid EmailDTO emailDTO, BindingResult errors, HttpServletResponse response) throws MessagingException, TemplateException, NoSuchAlgorithmException, IOException {
        if (errors.hasErrors()) {
            try {
                return ResponseEntity.badRequest().body(
                        new ObjectMapper().writeValueAsString
                                (errors.getAllErrors()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                (ObjectError oE) -> oE.getCode(),
                                                oE -> oE.getDefaultMessage()))));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        if (userService.getUserByEmail(emailDTO.getEmail()) != null) {
            emailService.sendResetPasswordEmail(emailDTO.getEmail());
//            response.sendRedirect("http://localhost:8080/");
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/{token}")
    public String checkToken(@PathVariable Long id, @PathVariable String token, Model model) {

        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getToken().equals(token)) {
                return "redirect:/resetPassword/" + id + "/" + token;
            }
        }
        return null;
    }


    @PutMapping("/resetPassword")
    public ResponseEntity ResetPassword(@Valid PasswordDTO passwordDTO, BindingResult errors, HttpServletResponse response) throws IOException {
        if (errors.hasErrors()) {
            try {
                return ResponseEntity.badRequest().body(
                        new ObjectMapper().writeValueAsString
                                (errors.getAllErrors()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                (ObjectError oE) -> oE.getCode(),
                                                oE -> oE.getDefaultMessage()))));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        Optional<User> userOptional = userService.getUserById(passwordDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getToken().equals(passwordDTO.getToken())) {
                user.setPassword(passwordDTO.getPassword());
                userService.updateUserPassword(user);
                response.sendRedirect("http://localhost:8080/login");
            }



        }
        return ResponseEntity.noContent().build();

    }
}