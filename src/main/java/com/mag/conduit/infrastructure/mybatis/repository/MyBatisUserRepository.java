package com.mag.conduit.infrastructure.mybatis.repository;

import com.mag.conduit.application.dto.UserData;
import com.mag.conduit.core.user.User;
import com.mag.conduit.core.user.UserRepository;
import com.mag.conduit.infrastructure.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MyBatisUserRepository implements UserRepository {
    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserData> findById(UUID id) {
        return userMapper.findById(id);
    }

    @Override
    public Optional<UserData> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(UUID id, String username, String email, String password, String bio, String image) {
        userMapper.update(id, username, email, password, bio, image);
    }

    @Override
    public Optional<String> getPasswordHashFromEmail(String email) {
        return userMapper.getPasswordHashFromEmail(email);
    }
}
