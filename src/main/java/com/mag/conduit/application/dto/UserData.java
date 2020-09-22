package com.mag.conduit.application.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.mag.conduit.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
@JsonRootName("user")
public class UserData {
    final private UUID id;
    final private String username;
    final private String email;
    final private String bio;
    final private String image;

    // UserQueryService gets a User from the repository, but must not return
    // the password to the controller, so we send it a UserData object instead
    public static UserData from(User user) {
        return new UserData(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getImage()
        );
    }
}
