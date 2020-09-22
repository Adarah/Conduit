package com.mag.conduit.application.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProfileData {
    private String username;
    private String bio;
    private String image;
    private Boolean following;

    public ProfileData (UserData userData, boolean following) {
            this.username = userData.getUsername();
            this.bio = userData.getBio();
            this.image = userData.getImage();
            this.following = following;
    }
}
