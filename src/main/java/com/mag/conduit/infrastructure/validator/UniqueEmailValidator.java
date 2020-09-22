package com.mag.conduit.infrastructure.validator;

import com.mag.conduit.application.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserQueryService userQueryService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (userQueryService.findByEmail(s).isPresent()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Email was already taken")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
