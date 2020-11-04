package pl.coderslab.charity.user.validation.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.coderslab.charity.user.entity.User;
import pl.coderslab.charity.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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

    public boolean EmailCheck (String email) {
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        return optionalUser.isPresent();
    }

    public boolean checkUniqueEmailWithoutCurrent(String email) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Principal userPrincipal = request.getUserPrincipal();
        List <User> list = userRepository.findAll();
        list.remove(userRepository.findUserByEmail(userPrincipal.getName()));
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        boolean result;
        result = optionalUser.filter(user -> list.stream().anyMatch(Predicate.isEqual(user))).isPresent();
        return !result;
    }


    }

