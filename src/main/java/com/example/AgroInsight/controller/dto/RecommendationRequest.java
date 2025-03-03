package com.example.AgroInsight.controller.dto;

public class RecommendationRequest {
    private Long userId;
    private double nitrogen;
    private double phosphorus;
    private double potassium;
    private double temperature;
    private double humidity;
    private double pH;
    private double rainfall;
    private String recommendedCrop;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public double getNitrogen() { return nitrogen; }
    public void setNitrogen(double nitrogen) { this.nitrogen = nitrogen; }

    public double getPhosphorus() { return phosphorus; }
    public void setPhosphorus(double phosphorus) { this.phosphorus = phosphorus; }

    public double getPotassium() { return potassium; }
    public void setPotassium(double potassium) { this.potassium = potassium; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getpH() { return pH; }
    public void setpH(double pH) { this.pH = pH; }

    public double getRainfall() { return rainfall; }
    public void setRainfall(double rainfall) { this.rainfall = rainfall; }

    public String getRecommendedCrop() { return recommendedCrop; }
    public void setRecommendedCrop(String recommendedCrop) { this.recommendedCrop = recommendedCrop; }
}
