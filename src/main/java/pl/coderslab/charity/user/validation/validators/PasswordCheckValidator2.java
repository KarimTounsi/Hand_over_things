package pl.coderslab.charity.user.validation.validators;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.DTOS.UserPasswordDTO;
import pl.coderslab.charity.user.validation.constraints.PasswordCheck2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordCheckValidator2 implements ConstraintValidator <PasswordCheck2, UserPasswordDTO> {

    @Override
    public boolean isValid(UserPasswordDTO userPasswordDTO, ConstraintValidatorContext constraintContext) {
        String password = userPasswordDTO.getPassword();
        String passwordCheck = userPasswordDTO.getPasswordCheck();

        boolean isPasswordsEqual = password.equals(passwordCheck);

        if (!isPasswordsEqual){
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("{passwordCheck.validation.message}")
                    .addPropertyNode("passwordCheck").addConstraintViolation();
        }
        return isPasswordsEqual;
    }
}
