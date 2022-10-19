package com.shakhner.sensorRestProject.weatherSensorRestProject.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MeasurementDTO {

    @Min(value = -100, message = "minimum temperature value is -100")
    @Max(value = 100, message = "maximum temperature value is -100")
    private Double temperatureValue;

    private Boolean raining;

    @Min(value = 0, message = "wind speed can not be less then 0")
    @Max(value = 140, message = "maximum value of wind speed is 140")
    private Double windSpeed;

    private Sensor sensor;

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
