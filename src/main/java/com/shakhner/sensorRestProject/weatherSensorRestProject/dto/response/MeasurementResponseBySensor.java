package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Response when requesting by name of Sensor, therefore response not contain it")
public class MeasurementResponseBySensor {
    @Min(value = -100, message = "minimum temperature value is -100")
    @Max(value = 100, message = "maximum temperature value is -100")
    @NotNull(message = "Temperature value should not be empty")
    private Double temperatureValue;

    @NotNull(message = "Raining value should not be empty")
    private Boolean raining;

    @Min(value = 0, message = "wind speed can not be less then 0")
    @Max(value = 140, message = "maximum value of wind speed is 140")
    private Double windSpeed;

    private String locationOfMeasurement;

    private String timeOfMeasurement;


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

    public String getTimeOfMeasurement() {
        return timeOfMeasurement;
    }

    public void setTimeOfMeasurement(String timeOfMeasurement) {
        this.timeOfMeasurement = timeOfMeasurement;
    }

    public String getLocationOfMeasurement() {
        return locationOfMeasurement;
    }

    public void setLocationOfMeasurement(String locationOfMeasurement) {
        this.locationOfMeasurement = locationOfMeasurement;
    }
}
