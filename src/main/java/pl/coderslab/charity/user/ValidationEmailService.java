package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
