package pl.coderslab.charity.user.validation.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.validation.constraints.UniqueEmailWithoutCurrent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidatorWithoutCurrent implements ConstraintValidator<UniqueEmailWithoutCurrent, String> {

    private final ValidationEmailService validationEmailService;

    @Override
    public boolean isValid(String source, ConstraintValidatorContext context) {
        return validationEmailService.checkUniqueEmailWithoutCurrent(source);
    }
}
