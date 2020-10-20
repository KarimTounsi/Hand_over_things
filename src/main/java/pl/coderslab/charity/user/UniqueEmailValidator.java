package pl.coderslab.charity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final ValidationEmailService validationEmailService;

    @Override
    public boolean isValid(String source, ConstraintValidatorContext context) {
        return validationEmailService.checkUniqueEmail(source);
    }
}
