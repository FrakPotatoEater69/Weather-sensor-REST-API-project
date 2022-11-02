package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "In Measurements responses we need only name of Sensor, without it's location")
public class SensorResponse {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
