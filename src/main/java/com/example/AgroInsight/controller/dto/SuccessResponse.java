package com.example.AgroInsight.controller.dto;

public class SuccessResponse {
    private String message = "Success";

    public SuccessResponse() {
        this.message = "Success";
    }

    public SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
