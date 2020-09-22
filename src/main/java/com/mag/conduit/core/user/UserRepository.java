package com.mag.conduit.core.user;

import com.mag.conduit.application.dto.UserData;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<UserData> findById(UUID id);

    Optional<UserData> findByUsername(String username);

    Optional<UserData> findByEmail(String email);

    void save(User user);

    void update(UUID id, String username, String email, String password, String bio, String image);

    Optional<String> getPasswordHashFromEmail(String email);
}
