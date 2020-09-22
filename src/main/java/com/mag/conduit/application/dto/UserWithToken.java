package com.mag.conduit.application.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

@Getter
@JsonRootName("user")
public class UserWithToken {
    private String username;
    private String email;
    private String bio;
    private String image;
    private String token;

    public UserWithToken(UserData userdata, String token) {
        this.email = userdata.getEmail();
        this.token = token;
        this.username = userdata.getUsername();
        this.bio = userdata.getBio();
        this.image = userdata.getImage();
    }
}
