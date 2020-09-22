package com.mag.conduit.infrastructure.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    String message() default "Invalid username or password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
