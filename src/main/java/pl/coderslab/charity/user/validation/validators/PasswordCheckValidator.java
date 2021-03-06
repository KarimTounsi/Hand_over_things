package pl.coderslab.charity.user.validation.validators;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.validation.constraints.PasswordCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordCheckValidator implements ConstraintValidator <PasswordCheck, UserDTO> {

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintContext) {
        String password = userDTO.getPassword();
        String passwordCheck = userDTO.getPasswordCheck();

        boolean isPasswordsEqual = password.equals(passwordCheck);

        if (!isPasswordsEqual){
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("{passwordCheck.validation.message}")
                    .addPropertyNode("passwordCheck").addConstraintViolation();
        }
        return isPasswordsEqual;
    }
}
