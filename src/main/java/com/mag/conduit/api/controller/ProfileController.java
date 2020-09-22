package com.mag.conduit.api.controller;

import com.mag.conduit.application.ProfileQueryService;
import com.mag.conduit.application.UserQueryService;
import com.mag.conduit.application.dto.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    ProfileQueryService profileQueryService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username,
                                        @AuthenticationPrincipal UUID currentUserId) {
        ProfileData profile = profileQueryService.findProfile(username, currentUserId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return ResponseEntity.ok().body(profile);
    }

}
