package pl.coderslab.charity.user.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.charity.email.service.EmailService;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;
import pl.coderslab.charity.user.service.UserService;
import pl.coderslab.charity.user.validation.validators.ValidationEmailService;

import javax.sql.DataSource;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRegisterController.class)
public class UserRegisterControllerTest {

    @MockBean
    private DataSource dataSource;

    @MockBean
    private UserService userService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private ValidationEmailService validationEmailService;


    @MockBean
    private UserRepository userRepository;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String email = "Email@interia.pl";
    private final String password = "Password123!1";

    @Before
    public void setUp2() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        when(validationEmailService.checkUniqueEmail(email)).thenReturn(true);

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(password);
        userDTO.setPasswordCheck(password);
        userDTO.setEmail(email);

        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword(password);

        when(userService.saveUser(userDTO)).thenReturn(user);


    }

    private static String REGISTER_PAGE = "user/register";

    @Test
    public void test_register_action_return_static_import() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTER_PAGE));
    }


    @Test
    public void test_show_page_and_attribute() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTER_PAGE))
                .andExpect(model().attributeExists("userDTO"));
    }


    @Test
    public void when_save_valid_data_then_redirect() throws Exception {
        String password = "Password123!1";
        mockMvc.perform(post("/register").param("email", email)
                .param("password", password).param("passwordCheck", password))
                .andExpect(redirectedUrl("/register/confirm"))
                .andDo(print());
    }


}