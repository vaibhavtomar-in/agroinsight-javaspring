package com.example.AgroInsight.exception;

public class ResourceNotFoundException extends LogitrackException {

    public ResourceNotFoundException(LogitracError e) {
        super(e);
    }

    public ResourceNotFoundException(LogitracError e, String message) {
        super(e, message);
    }
}
