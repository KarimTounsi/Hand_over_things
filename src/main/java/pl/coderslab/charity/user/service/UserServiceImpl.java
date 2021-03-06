package pl.coderslab.charity.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(UserDTO userDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setActive(false);
        user.setRole("ROLE_USER");

        String digest = getDigest();

        user.setToken(digest);

        return userRepository.save(user);
    }



    @Override
    public User activateUser(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllByActiveAndRoleOrderById(Boolean status, String role) {
        return userRepository.findAllByActiveAndRoleOrderById(status, role);
    }

    @Override
    public User saveUserAdmin(AdminDTO adminDTO) {
        User user = new User();
        user.setEmail(adminDTO.getEmail());
        user.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        user.setActive(true);
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
        log.info("Admin saved: " + user);
        return user;
    }

    @Override
    public User ActiveAdminById(Long id) {
        return userRepository.findByActiveAndRoleAndIdOrderById(true, "ROLE_ADMIN", id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User updated: " + user);
        return user;
    }

    @Override
    public User updateUserPartially(User user) {
        userRepository.save(user);
        log.info("User updatedPartially: " + user);
        return user;
    }

    @Override
    public User updateUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User passwordUpdated: " + user);
        return user;
    }

    @Override
    public User updateUserEmail(User user) {
        userRepository.save(user);
        log.info("User emailUpdated: " + user);
        return user;
    }


}
