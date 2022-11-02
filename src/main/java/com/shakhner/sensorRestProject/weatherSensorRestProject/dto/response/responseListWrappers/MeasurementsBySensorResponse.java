package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.MeasurementResponseBySensor;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "Response when it should store the list of MeasurementResponseBySensor objects")
public class MeasurementsBySensorResponse {

    private List<MeasurementResponseBySensor> measurements;

    public MeasurementsBySensorResponse() {
    }

    public MeasurementsBySensorResponse(List<MeasurementResponseBySensor> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementResponseBySensor> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementResponseBySensor> measurements) {
        this.measurements = measurements;
    }
}
