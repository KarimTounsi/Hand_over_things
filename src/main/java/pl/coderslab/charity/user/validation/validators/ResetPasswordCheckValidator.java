package pl.coderslab.charity.user.validation.validators;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.DTOS.PasswordDTO;
import pl.coderslab.charity.user.DTOS.UserDTO;
import pl.coderslab.charity.user.validation.constraints.PasswordCheck;
import pl.coderslab.charity.user.validation.constraints.ResetPasswordCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ResetPasswordCheckValidator implements ConstraintValidator <ResetPasswordCheck, PasswordDTO> {

    @Override
    public boolean isValid(PasswordDTO passwordDTO, ConstraintValidatorContext constraintContext) {
        String password = passwordDTO.getPassword();
        String passwordCheck = passwordDTO.getPasswordCheck();

        boolean isPasswordsEqual = password.equals(passwordCheck);

        if (!isPasswordsEqual){
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("{passwordCheck.validation.message}")
                    .addPropertyNode("passwordCheck").addConstraintViolation();
        }
        return isPasswordsEqual;
    }
}
