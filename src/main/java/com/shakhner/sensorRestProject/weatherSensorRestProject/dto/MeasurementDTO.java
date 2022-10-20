package com.shakhner.sensorRestProject.weatherSensorRestProject.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.MeasurementExceprions.MeasurementNotCreatedException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class MeasurementDTO {

    @Min(value = -100, message = "minimum temperature value is -100")
    @Max(value = 100, message = "maximum temperature value is -100")
    @NotNull(message = "Temperature value should not be empty")
    private Double temperatureValue;

    @NotNull(message = "Raining value should not be empty")
    private Boolean raining;

    @Min(value = 0, message = "wind speed can not be less then 0")
    @Max(value = 140, message = "maximum value of wind speed is 140")
    private Double windSpeed;

    @NotNull(message = "Input time of measurement")
    private String stringTimeOfMeasurement;

    private Date timeOfMeasurement;
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

    public String getStringTimeOfMeasurement() {
        return stringTimeOfMeasurement;
    }

    public void setStringTimeOfMeasurement(String stringTimeOfMeasurement) {
        this.stringTimeOfMeasurement = stringTimeOfMeasurement;
    }

    public Date getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(Date timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }
}
