package com.example.AgroInsight.manager;

import com.example.AgroInsight.manager.data.RecommendationInfo;

import java.util.List;

public interface RecommendationManager {
    void saveRecommendation(RecommendationInfo info);
    List<RecommendationInfo> getRecommendationHistory(Long userId);
}
