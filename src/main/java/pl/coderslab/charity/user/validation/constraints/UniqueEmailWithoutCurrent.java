package pl.coderslab.charity.user.validation.constraints;



import pl.coderslab.charity.user.validation.validators.UniqueEmailValidatorWithoutCurrent;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidatorWithoutCurrent.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailWithoutCurrent {
    String message() default "{UniqueEmailWithoutCurrent.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
