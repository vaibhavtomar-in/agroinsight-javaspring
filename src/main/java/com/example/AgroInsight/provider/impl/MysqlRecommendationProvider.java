package com.example.AgroInsight.provider.impl;

import com.example.AgroInsight.entities.Recommendation;
import com.example.AgroInsight.entities.User;
import com.example.AgroInsight.exception.LogitracError;
import com.example.AgroInsight.exception.LogitrackException;
import com.example.AgroInsight.manager.data.RecommendationInfo;
import com.example.AgroInsight.provider.RecommendationProvider;
import com.example.AgroInsight.repositories.RecommendationRepository;
import com.example.AgroInsight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MysqlRecommendationProvider implements RecommendationProvider {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveRecommendation(RecommendationInfo info) {
        User user = userRepository.findById(info.getUserId()).orElseThrow(() ->
                new LogitrackException(LogitracError.ACCOUNT_DOES_NOT_EXIST));

        Recommendation recommendation = transform(info, user);
        recommendationRepository.save(recommendation);
    }

    @Override
    public List<RecommendationInfo> getRecommendationHistory(Long userId) {
        List<Recommendation> recommendations = recommendationRepository.findByUserId(userId);
        return recommendations.stream().map(this::transform).collect(
                Collectors.toList());
    }

    private RecommendationInfo transform(Recommendation recommendation) {
        RecommendationInfo info = new RecommendationInfo();
        info.setUserId(recommendation.getUser().getId());
        info.setNitrogen(recommendation.getNitrogen());
        info.setPhosphorus(recommendation.getPhosphorus());
        info.setPotassium(recommendation.getPotassium());
        info.setTemperature(recommendation.getTemperature());
        info.setHumidity(recommendation.getHumidity());
        info.setpH(recommendation.getpH());
        info.setRainfall(recommendation.getRainfall());
        info.setRecommendedCrop(recommendation.getRecommendedCrop());
        info.setDate(recommendation.getDate().toLocalDate());
        return info;
    }

    private Recommendation transform(RecommendationInfo info, User user) {
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setNitrogen(info.getNitrogen());
        recommendation.setPhosphorus(info.getPhosphorus());
        recommendation.setPotassium(info.getPotassium());
        recommendation.setTemperature(info.getTemperature());
        recommendation.setHumidity(info.getHumidity());
        recommendation.setpH(info.getpH());
        recommendation.setRainfall(info.getRainfall());
        recommendation.setRecommendedCrop(info.getRecommendedCrop());
        recommendation.setDate(LocalDateTime.now());

        return recommendation;
    }
}
