package com.example.AgroInsight.provider;

import com.example.AgroInsight.manager.data.RecommendationInfo;

import java.util.List;

public interface RecommendationProvider {
    void saveRecommendation(RecommendationInfo info);
    List<RecommendationInfo> getRecommendationHistory(Long userId);
}
