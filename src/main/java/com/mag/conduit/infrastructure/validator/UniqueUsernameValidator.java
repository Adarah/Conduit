package com.mag.conduit.infrastructure.validator;

import com.mag.conduit.application.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    UserQueryService userQueryService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (userQueryService.findByUsername(s).isPresent()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Username was already taken")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
