package com.mag.conduit.api.controller;

import com.mag.conduit.application.JwtService;
import com.mag.conduit.application.UserQueryService;
import com.mag.conduit.application.dto.UserData;
import com.mag.conduit.application.dto.UserWithToken;
import com.mag.conduit.application.dto.form.LoginForm;
import com.mag.conduit.application.dto.form.SignupForm;
import com.mag.conduit.core.user.User;
import com.mag.conduit.infrastructure.validator.DefaultBeforeExpensiveSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@Validated
public class AuthController {
    @Autowired
    UserQueryService userQueryService;

    @Autowired
    JwtService jwtService;

    @Value("${image.default}")
    private String defaultImage;

    @PostMapping
    public ResponseEntity<?> signup(@Validated(DefaultBeforeExpensiveSequence.class) @RequestBody SignupForm signupForm) {
        User newUser = new User(
                signupForm.getEmail(),
                signupForm.getUsername(),
                signupForm.getPassword(),
                "",
                defaultImage
        );
        userQueryService.save(newUser);
        UserData userData = userQueryService.findById(newUser.getId()).get();
        String token = jwtService.generateToken(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserWithToken(userData, token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        UserData userData = userQueryService.findByEmail(loginForm.getEmail()).get();
        String token = jwtService.generateToken(userData);
        return ResponseEntity.status(HttpStatus.OK).body(new UserWithToken(userData, token));
    }
}

