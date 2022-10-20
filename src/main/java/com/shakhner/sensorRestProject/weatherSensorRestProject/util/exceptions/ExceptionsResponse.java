package com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions;

public class ExceptionsResponse {
    private String message;
    private long timestamp;

    public ExceptionsResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
