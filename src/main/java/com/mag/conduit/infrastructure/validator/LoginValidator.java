package com.mag.conduit.infrastructure.validator;


import com.mag.conduit.application.UserQueryService;
import com.mag.conduit.application.dto.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, LoginForm> {
    @Autowired
    UserQueryService userQueryService;

    @Override
    public void initialize(Login constraintAnnotation) {

    }

    @Override
    public boolean isValid(LoginForm loginForm, ConstraintValidatorContext constraintValidatorContext) {
        return userQueryService.validateLogin(loginForm.getEmail(), loginForm.getPassword());
    }

}
