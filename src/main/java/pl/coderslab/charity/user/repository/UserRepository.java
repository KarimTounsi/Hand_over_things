package pl.coderslab.charity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findUserByEmail(String email);

    List<User> findAllByActiveAndRoleOrderById(Boolean status, String role);

    User findByActiveAndRoleAndIdOrderById(Boolean status, String role, Long id);

}
