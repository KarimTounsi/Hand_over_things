package pl.coderslab.charity.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.institution.Institution;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api/admins")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    @GetMapping
    public ResponseEntity<List<User>> findAllWithStatusTrue() {

        List<User> Admins = userService.getAllByActiveAndRoleOrderById(true, "ROLE_ADMIN");

        return ResponseEntity.ok(Admins);
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

//    @PutMapping("/{id}")
//    public ResponseEntity updateOne(@RequestBody User user) {
//        Optional<User> optionalUser = userService.getUserById(user.getId());
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setEmail(institution.getDescription());
//            user.setPassword(institution.getDescription());
//
//            institutionService.saveInstitution(institutionInDb);
//        }
//        return ResponseEntity.noContent().build();
//    }

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
       if (updateMap.get("email") !="" ||  updateMap.get("password") !="") {
           if (updateMap.containsKey("email") && updateMap.get("email") !=""){
               user.setEmail(updateMap.get("email"));
           }
           if (updateMap.containsKey("password") && updateMap.get("password") !=""){
               user.setPassword(passwordEncoder.encode(updateMap.get("password")));
           }
       }
            userService.updateUserAdminPartially(user);
        }
        return ResponseEntity.noContent().build();
    }

}
