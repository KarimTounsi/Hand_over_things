package pl.coderslab.charity.user.controllers.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.service.UserService;
import pl.coderslab.charity.user.entity.User;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/{role}")
    public ResponseEntity<List<User>> findAllWithStatusTrue(@PathVariable String role, Principal principal) {
        List<User> users;
        if (role.equals("ROLE_ADMIN")) {
            users = userService.getAllByActiveAndRoleOrderById(true, role);
            users.remove(userService.getUserByEmail(principal.getName()));
        } else {
            users = userService.getAllByActiveAndRoleOrderById(true, role);
        }

        return ResponseEntity.ok(users);
    }


    @GetMapping("/{role}/{id}")
    public ResponseEntity getOne(@PathVariable Long id, @PathVariable String role, Principal principal) {
        User LoggedUser = userService.getUserByEmail(principal.getName());
        if (LoggedUser.getRole().equals("ROLE_USER") && LoggedUser.getId().equals(id)) {
            Optional<User> optionalUser = userService.getUserById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (LoggedUser.getRole().equals("ROLE_ADMIN")) {
            Optional<User> optionalUser = userService.getUserById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else return ResponseEntity.notFound().build();

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
        return ResponseEntity.created(URI.create("/api/user/" + saved.getId()))
                .build();
    }


    @PutMapping("/{id}")
    public ResponseEntity updateOne(@Valid @RequestBody AdminDTO userDTO, BindingResult errors, Principal principal) {
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
        User LoggedUser = userService.getUserByEmail(principal.getName());
        if (LoggedUser.getRole().equals("ROLE_USER") && LoggedUser.getId().equals(userDTO.getId())) {
            Optional<User> optionalUser = userService.getUserById(userDTO.getId());
            if (optionalUser.isPresent()) {
                User userDb = optionalUser.get();
                userDb.setEmail(userDTO.getEmail());
                userDb.setPassword(userDTO.getPassword());
                userService.updateUser(userDb);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else if (LoggedUser.getRole().equals("ROLE_ADMIN")) {
            Optional<User> optionalUser = userService.getUserById(userDTO.getId());
            if (optionalUser.isPresent()) {
                User userDb = optionalUser.get();
                userDb.setEmail(userDTO.getEmail());
                userDb.setPassword(userDTO.getPassword());
                userService.updateUser(userDb);
            } else {
                return ResponseEntity.noContent().build();
            }
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
            userService.updateUserPartially(user);
        }
        return ResponseEntity.noContent().build();
    }


}