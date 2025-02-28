package com.example.AgroInsight.controller;

import com.example.AgroInsight.controller.dto.*;
import com.example.AgroInsight.manager.UserManager;
import com.example.AgroInsight.manager.data.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserManager userManager;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello, AgroInsight is working!");
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signup(HttpServletRequest request, @RequestBody NewUserRequest userRequest) {
        UserInfo userInfo = transform(userRequest);
        userManager.registerUser(userInfo);
        return ResponseEntity.ok(new SuccessResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public UserId loginUser(@RequestBody UserLoginRequest loginRequest) {
        com.example.AgroInsight.manager.data.UserId userId = userManager.loginUser(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        UserId userId1 = new UserId();
        userId1.setUserId(userId.getUserId());
        return  userId1;
    }

    @GetMapping("/details")
    public UserDetails getUserDetails(HttpServletRequest request, @RequestParam Long userId) {
        UserInfo userInfo = userManager.getUserDetails(userId);
        return transform(userInfo);
    }

    private UserDetails transform(UserInfo userInfo) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userInfo.getName());
        userDetails.setPhoneNumber(userInfo.getPhoneNumber());
        userDetails.setPincode(userInfo.getPincode());
        userDetails.setState(userInfo.getState());
        userDetails.setCountry(userInfo.getCountry());
        return userDetails;
    }

    private UserInfo transform(NewUserRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(request.getName());
        userInfo.setCountry(request.getCountry());
        userInfo.setPassword(request.getPassword());
        userInfo.setPhoneNumber(request.getPhoneNumber());
        userInfo.setPincode(request.getPincode());
        userInfo.setState(request.getState());
        return userInfo;
    }
}
