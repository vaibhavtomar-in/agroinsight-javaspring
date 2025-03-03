package com.example.AgroInsight.manager.impl;

import com.example.AgroInsight.manager.RecommendationManager;
import com.example.AgroInsight.manager.data.RecommendationInfo;
import com.example.AgroInsight.provider.RecommendationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationManagerImpl implements RecommendationManager {

    @Autowired
    private RecommendationProvider recommendationProvider;

    @Override
    public void saveRecommendation(RecommendationInfo info) {
        recommendationProvider.saveRecommendation(info);
    }

    @Override
    public List<RecommendationInfo> getRecommendationHistory(Long userId) {
        return recommendationProvider.getRecommendationHistory(userId);
    }
}
