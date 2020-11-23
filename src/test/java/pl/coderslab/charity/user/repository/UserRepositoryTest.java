package pl.coderslab.charity.user.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.charity.user.entity.User;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private User user2;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("email");
        user.setToken("token");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        user.setActive(true);

        user2 = new User();
        user2.setEmail("email2");
        user2.setToken("token2");
        user2.setPassword("password2");
        user2.setRole("ROLE_USER");
        user2.setActive(true);

    }

    @Test
    public void find_by_email_then_return_User() {

        entityManager.persist(user);

        User userByEmail = userRepository.findUserByEmail("email");

        assertEquals(user.getId(), userByEmail.getId());
        assertThat(user.getEmail(), is(userByEmail.getEmail()));
    }

    @Test
    public void find_first_by_email_then_return_User() {

        entityManager.persist(user);

        User userByEmail = userRepository.findFirstByEmail("email").orElseThrow();

        assertEquals(user.getId(), userByEmail.getId());
        assertThat(user.getEmail(), is(userByEmail.getEmail()));
    }


    @Test
    public void find_all_user_by_active_and_role_order_by_id_then_return_List_of_users() {


        entityManager.persist(user);
        entityManager.persist(user2);

        List<User> userList = userRepository.findAllByActiveAndRoleOrderById(true,"ROLE_USER");

        assertThat(userList, hasItem(anyOf(hasProperty("email", is("email")), hasProperty("password" , is("password")))));

        assertEquals(user, userList.stream().findFirst().orElseThrow());
        assertEquals(user.getId(), userList.stream().findFirst().orElseThrow().getId());
        assertEquals(user2, userList.get(userList.size()-1));
    }

    @Test
    public void find_user_by_active_and_role_and_id_order_by_id_then_return_user() {

        entityManager.persist(user);
        entityManager.persist(user2);

        User actualUser = userRepository.findByActiveAndRoleAndIdOrderById(true,"ROLE_USER", 2L);

        assertEquals(user2, actualUser);
        assertEquals(user2.getId(), actualUser.getId());
        assertEquals(user2.getEmail(), actualUser.getEmail());
    }


}