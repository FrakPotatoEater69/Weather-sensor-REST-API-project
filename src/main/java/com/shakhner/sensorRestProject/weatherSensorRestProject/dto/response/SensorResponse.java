package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorResponse {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
