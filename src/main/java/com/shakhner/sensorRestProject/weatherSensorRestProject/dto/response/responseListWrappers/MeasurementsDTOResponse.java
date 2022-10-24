package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;

import java.util.List;

public class MeasurementsDTOResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementsDTOResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public MeasurementsDTOResponse(){}

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
