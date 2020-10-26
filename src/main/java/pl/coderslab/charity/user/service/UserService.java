package pl.coderslab.charity.user.service;

import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserDTO userDTO);
    User getUserByEmail(String email);
    List<User> getAllByActiveAndRoleOrderById(Boolean status, String role);


    User saveUserAdmin(AdminDTO adminDTO);
    User ActiveAdminById(Long id);

    Optional <User> getUserById(Long id);

    void save(User user);

    User updateUserAdmin(User user);

   User updateUserAdminPartially(User user);

   User updateUser(User user) ;

   User updateUserPartially(User user);

    User updateUserPassword(User user);
    User updateUserEmail(User user);

}
