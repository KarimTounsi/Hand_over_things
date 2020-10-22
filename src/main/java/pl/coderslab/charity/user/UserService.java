package pl.coderslab.charity.user;

public interface UserService {

    void saveUser(UserDTO userDTO);
    User getUserByEmail(String email);

}
