package com.example.AgroInsight.provider;

import com.example.AgroInsight.entities.User;
import com.example.AgroInsight.manager.data.UserInfo;

import java.util.Optional;

public interface UserProvider {
    User createUser(UserInfo userInfo);
    Optional<User> getUserByPhoneNumber(String phoneNumber);
    Optional<UserInfo> getUserById(Long userId);
}
