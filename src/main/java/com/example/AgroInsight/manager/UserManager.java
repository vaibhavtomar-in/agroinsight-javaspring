package com.example.AgroInsight.manager;

import com.example.AgroInsight.manager.data.UserId;
import com.example.AgroInsight.manager.data.UserInfo;
import com.example.AgroInsight.entities.User;

public interface UserManager {
    void registerUser(UserInfo userInfo);
    UserId loginUser(String phoneNumber, String password);
    UserInfo getUserDetails(Long userId);
}
