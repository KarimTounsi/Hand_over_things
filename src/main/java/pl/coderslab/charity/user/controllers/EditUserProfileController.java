package pl.coderslab.charity.user.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.charity.user.DTOS.UserEmailDTO;
import pl.coderslab.charity.user.DTOS.UserPasswordDTO;

import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user/api/edit-profile")
@RequiredArgsConstructor
@Slf4j
public class EditUserProfileController {

    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/email/{id}")
    public ResponseEntity updateEmail(@Valid @RequestBody UserEmailDTO userEmailDTO, BindingResult errors, HttpServletResponse response) throws IOException {
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
        Optional<User> optionalUser = userService.getUserById(userEmailDTO.getId());
        if (optionalUser.isPresent()) {
            User userDb = optionalUser.get();
            userDb.setEmail(userEmailDTO.getEmail());
            userService.updateUserEmail(userDb);
//            response.sendRedirect("/logout");
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/password/{id}")
    public ResponseEntity updatePassword(@Valid @RequestBody UserPasswordDTO userPasswordDTO, BindingResult errors) {
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
        Optional<User> optionalUser = userService.getUserById(userPasswordDTO.getId());
        if (optionalUser.isPresent()) {
            User userDb = optionalUser.get();
            userDb.setPassword(userPasswordDTO.getPassword());
            userService.updateUserPassword(userDb);
        }
        return ResponseEntity.noContent().build();
    }


}
