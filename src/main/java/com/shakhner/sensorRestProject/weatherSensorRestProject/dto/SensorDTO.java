package com.shakhner.sensorRestProject.weatherSensorRestProject.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SensorDTO {

    @NotEmpty(message = "name of sensor should not be empty")
    @Size(min = 3, max = 30, message = "sensor name should be between 3 and 30 characters")
    private String name;

    @Size(min = 3, max = 30, message = "location name should be between 3 and 30 characters")
    private String location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
