package com.mag.conduit.application;

import com.mag.conduit.application.dto.UserData;
import com.mag.conduit.core.user.User;
import com.mag.conduit.core.userFollowRelation.UserFollowRepository;
import com.mag.conduit.core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserQueryService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Optional<UserData> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UserData> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<UserData> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean validateLogin(String formEmail, String formPassword) {
        return userRepository
                .getPasswordHashFromEmail(formEmail)
                // hardcoded Bcrypt.checkpw could be bad, maybe should be abstracted like encoder was?
                .map(passwordHash -> BCrypt.checkpw(formPassword, passwordHash))
                .orElse(false);
    }

    @Transactional
    public void update(UUID id, String username, String email, String password, String bio, String image) {
        if (password != null) {
            password = encoder.encode(password);
        }
        userRepository.update(id, username, email, password, bio, image);
    }

    @Transactional(readOnly = true)
    public boolean isUsernameTaken(String username) {
        return findByUsername(username).isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isEmailTaken(String email) {
        return findByEmail(email).isPresent();
    }


}
