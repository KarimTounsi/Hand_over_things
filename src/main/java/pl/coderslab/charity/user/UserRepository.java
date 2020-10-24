package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findUserByEmail(String email);

    List<User> findAllByActiveAndRoleOrderById(Boolean status, String role);

    User findByActiveAndRoleAndIdOrderById(Boolean status, String role, Long id);

}
