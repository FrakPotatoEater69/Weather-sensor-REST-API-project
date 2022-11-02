package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.MeasurementResponseByLocation;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "Response when it should store the list of MeasurementResponseByLocation objects")
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
