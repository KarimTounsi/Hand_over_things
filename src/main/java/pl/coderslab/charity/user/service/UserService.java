package pl.coderslab.charity.user.service;

import pl.coderslab.charity.user.DTOS.AdminDTO;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {


    default String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (final byte b : in)
            builder.append(String.format("%02x", b));
        return builder.toString();
    }

    default String getDigest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = bytesToHex(salt.digest());
        return digest;
    }

    User saveUser(UserDTO userDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    User activateUser(User user);

    User getUserByEmail(String email);

    List<User> getAllByActiveAndRoleOrderById(Boolean status, String role);


    User saveUserAdmin(AdminDTO adminDTO);

    User ActiveAdminById(Long id);

    Optional<User> getUserById(Long id);

    void save(User user);


    User updateUser(User user);

    User updateUserPartially(User user);

    User updateUserPassword(User user);

    User updateUserEmail(User user);



}
