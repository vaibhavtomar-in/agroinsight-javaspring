package com.example.AgroInsight.controller;

import com.example.AgroInsight.controller.dto.*;
import com.example.AgroInsight.manager.RecommendationManager;
import com.example.AgroInsight.manager.UserManager;
import com.example.AgroInsight.manager.data.RecommendationInfo;
import com.example.AgroInsight.manager.data.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserManager userManager;
    @Autowired
    RecommendationManager recommendationManager;

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

    @PostMapping("/recommendation")
    public SuccessResponse saveRecommendation(@RequestBody RecommendationRequest request) {
        RecommendationInfo recommendationInfo = transform(request);
        recommendationManager.saveRecommendation(recommendationInfo);
        return new SuccessResponse("Recommendation Saved Successfully");
    }
    @GetMapping("/recommendation/history")
    public List<RecommendationHistoryResponse> getRecommendationHistory(@RequestParam Long userId) {
        List<RecommendationInfo> recommendationInfoList = recommendationManager.getRecommendationHistory(userId);
        return recommendationInfoList.stream().map(this::transform).toList();
    }

    private RecommendationHistoryResponse transform(RecommendationInfo info) {
        RecommendationHistoryResponse response = new RecommendationHistoryResponse();
        response.setNitrogen(info.getNitrogen());
        response.setPhosphorus(info.getPhosphorus());
        response.setPotassium(info.getPotassium());
        response.setTemperature(info.getTemperature());
        response.setHumidity(info.getHumidity());
        response.setpH(info.getpH());
        response.setRainfall(info.getRainfall());
        response.setRecommendedCrop(info.getRecommendedCrop());
        return response;
    }


    private RecommendationInfo transform(RecommendationRequest request) {
        RecommendationInfo info = new RecommendationInfo();
        info.setUserId(request.getUserId());
        info.setNitrogen(request.getNitrogen());
        info.setPhosphorus(request.getPhosphorus());
        info.setPotassium(request.getPotassium());
        info.setTemperature(request.getTemperature());
        info.setHumidity(request.getHumidity());
        info.setpH(request.getpH());
        info.setRainfall(request.getRainfall());
        info.setRecommendedCrop(request.getRecommendedCrop());
        return info;
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
