package com.mag.conduit.api.controller;

import com.mag.conduit.application.JwtService;
import com.mag.conduit.application.UserQueryService;
import com.mag.conduit.application.dto.UserData;
import com.mag.conduit.application.dto.UserWithToken;
import com.mag.conduit.application.dto.form.UpdateUserForm;
import com.mag.conduit.infrastructure.validator.DefaultBeforeExpensiveSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserQueryService userQueryService;

    @Autowired
    JwtService jwtService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UUID id,
                                            @RequestHeader(value = "Authorization") String authorization) {
        UserData userData = userQueryService.findById(id).orElseThrow(
                () -> new AuthorizationServiceException("Current principal was not found in the DB")
        );
        return ResponseEntity.ok(new UserWithToken(userData, authorization.split(" ")[1]));
    }

    @PutMapping
    public ResponseEntity<?> updateCurrentUser(
            @AuthenticationPrincipal UUID id,
            @RequestHeader("Authorization") String authorization,
            @Validated(DefaultBeforeExpensiveSequence.class) @RequestBody UpdateUserForm updateUserForm
    ) {
        userQueryService.update(
                id,
                updateUserForm.getUsername(),
                updateUserForm.getEmail(),
                updateUserForm.getPassword(),
                updateUserForm.getBio(),
                updateUserForm.getImage()
        );
        UserData userData = userQueryService.findById(id).get();
        return ResponseEntity.ok(new UserWithToken(userData, authorization));
    }
}
