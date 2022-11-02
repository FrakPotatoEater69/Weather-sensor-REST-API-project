package com.shakhner.sensorRestProject.weatherSensorRestProject.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Class for getting location info from API requests")
public class LocationWrapperDTO {

    @NotEmpty(message = "name of location should not be empty")
    @Size(min = 3, max = 30, message = "location's name should be between 3 and 30 characters")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
