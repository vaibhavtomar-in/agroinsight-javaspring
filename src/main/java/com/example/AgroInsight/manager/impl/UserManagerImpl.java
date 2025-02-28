package com.example.AgroInsight.manager.impl;

import com.example.AgroInsight.controller.dto.NewUserRequest;
import com.example.AgroInsight.entities.User;
import com.example.AgroInsight.exception.LogitracError;
import com.example.AgroInsight.exception.LogitrackException;
import com.example.AgroInsight.manager.UserManager;
import com.example.AgroInsight.manager.data.UserId;
import com.example.AgroInsight.manager.data.UserInfo;
import com.example.AgroInsight.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    UserProvider userProvider;

    public void registerUser(UserInfo userInfo) {
        userProvider.createUser(userInfo);
    }

    @Override
    public UserId loginUser(String phoneNumber, String password) {
        Optional<User> existingUser = userProvider.getUserByPhoneNumber(phoneNumber);

        if (existingUser.isEmpty()) {
            throw new LogitrackException(LogitracError.ACCOUNT_DOES_NOT_EXIST);
        }

        User user = existingUser.get();
        if (!user.getPassword().equals(password)) {
            throw new LogitrackException(LogitracError.INVALID_PASSWORD);
        }
        UserId userId = new UserId();
        userId.setUserId(existingUser.get().getId());
        return userId;
    }

    @Override
    public UserInfo getUserDetails(Long userId) {
        Optional<UserInfo> userInfo = userProvider.getUserById(userId);

        if (userInfo.isEmpty()) {
            throw new LogitrackException(LogitracError.ACCOUNT_DOES_NOT_EXIST);
        }

        return userInfo.get();
    }
}
