package pl.coderslab.charity.user.validation.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.validation.constraints.EmailCheck;
import pl.coderslab.charity.user.validation.constraints.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {

    private final ValidationEmailService validationEmailService;

    @Override
    public boolean isValid(String source, ConstraintValidatorContext context) {
        return validationEmailService.EmailCheck(source);
    }
}
