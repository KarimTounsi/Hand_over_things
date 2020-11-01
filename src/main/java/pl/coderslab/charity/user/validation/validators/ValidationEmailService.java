package pl.coderslab.charity.user.validation.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ValidationEmailService {

    private final UserRepository userRepository;


    public boolean checkUniqueEmail(String email) {
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        return !optionalUser.isPresent();
    }


    }

