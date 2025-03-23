package com.example.AgroInsight.provider.impl;

import com.example.AgroInsight.entities.User;
import com.example.AgroInsight.exception.LogitrackException;
import com.example.AgroInsight.exception.LogitracError;

import com.example.AgroInsight.manager.data.UserInfo;
import com.example.AgroInsight.provider.UserProvider;
import com.example.AgroInsight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MysqlUserProvider implements UserProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public MysqlUserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserInfo userInfo) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userInfo.getPhoneNumber());

        if (existingUser.isPresent()) {
            throw new LogitrackException(LogitracError.ACCOUNT_ALREADY_EXISTS);
        }
        User user = transform(userInfo);
        userRepository.save(user);
        user = userRepository.findByPhoneNumber(user.getPhoneNumber()).get();
        return user;
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<UserInfo> getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(this::transform);
    }

    private UserInfo transform(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getName());
        userInfo.setPhoneNumber(user.getPhoneNumber());
        userInfo.setPincode(user.getPincode());
        userInfo.setState(user.getState());
        userInfo.setCountry(user.getCountry());
        return userInfo;
    }

    private User transform(UserInfo userInfo) {
        User user = new User();
        user.setName(userInfo.getName());
        user.setPhoneNumber(userInfo.getPhoneNumber());
        user.setPassword(userInfo.getPassword()); // Encrypt password
        user.setPincode(userInfo.getPincode());
        user.setState(userInfo.getState());
        user.setCountry(userInfo.getCountry());
        return user;
    }
}
