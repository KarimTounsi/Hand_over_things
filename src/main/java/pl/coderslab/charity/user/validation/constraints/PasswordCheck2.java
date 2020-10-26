package pl.coderslab.charity.user.validation.constraints;


import pl.coderslab.charity.user.validation.validators.PasswordCheckValidator;
import pl.coderslab.charity.user.validation.validators.PasswordCheckValidator2;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordCheckValidator2.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheck2 {
    String message() default "{passwordCheck.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
