package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.MeasurementResponseByLocation;

import java.util.List;

public class MeasurementsByLocationResponse {
    private List<MeasurementResponseByLocation> response;

    public MeasurementsByLocationResponse(List<MeasurementResponseByLocation> response) {
        this.response = response;
    }

    public MeasurementsByLocationResponse() {
    }

    public List<MeasurementResponseByLocation> getResponse() {
        return response;
    }

    public void setResponse(List<MeasurementResponseByLocation> response) {
        this.response = response;
    }
}
