package pl.coderslab.charity.user.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<List<User>> findAllWithStatusTrue() {


        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        URI newUri = builder.build().toUri();

        System.out.println(newUri);
//        System.out.println(request.getRequestURL().toString() + "?" + request.getQueryString());


        List<User> ListUsers = null;
//        if () {
//        ListUsers = userService.getAllByActiveAndRoleOrderById(true, "ROLE_ADMIN");
//        } else if ()){
//            ListUsers = userService.getAllByActiveAndRoleOrderById(true, "ROLE_USER");
//        }
        return ResponseEntity.ok(ListUsers);
    }

    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody
                                            AdminDTO adminDTO, BindingResult errors) {
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
        User saved = userService.saveUserAdmin(adminDTO);
        return ResponseEntity.created(URI.create("/api/institutions/" + saved.getId()))
                .build();
    }


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


    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            userService.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity updateOne(@Valid @RequestBody AdminDTO adminDTO, BindingResult errors) {
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
        Optional<User> optionalUser = userService.getUserById(adminDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(adminDTO.getEmail());
            user.setPassword(adminDTO.getPassword());
            userService.updateUserAdmin(user);
        }
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity updatePartially(@PathVariable Long id, @RequestBody Map<String, String> updateMap) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (updateMap.get("email") != "" || updateMap.get("password") != "") {
                if (updateMap.containsKey("email") && updateMap.get("email") != "") {
                    user.setEmail(updateMap.get("email"));
                }
                if (updateMap.containsKey("password") && updateMap.get("password") != "") {
                    user.setPassword(passwordEncoder.encode(updateMap.get("password")));
                }
            }
            userService.updateUserAdminPartially(user);
        }
        return ResponseEntity.noContent().build();
    }

}
