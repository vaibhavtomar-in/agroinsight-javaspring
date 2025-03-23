package com.example.AgroInsight.manager;

import com.example.AgroInsight.manager.data.UserLogin;
import com.example.AgroInsight.manager.data.UserInfo;

public interface UserManager {
    UserLogin registerUser(UserInfo userInfo);
    UserLogin loginUser(String phoneNumber, String password);
    UserInfo getUserDetails(Long userId);
}
