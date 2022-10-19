package com.shakhner.sensorRestProject.weatherSensorRestProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int id;

    @Column(name = "temperature_value")
    @Min(value = -100, message = "minimum temperature value is -100")
    @Max(value = 100, message = "maximum temperature value is -100")
    @NotNull(message = "Temperature value should not be empty")
    private Double temperatureValue;

    @Column(name = "raining")
    @NotNull(message = "Raining value should not be empty")
    private Boolean raining;

    @Column(name = "wind_speed")
    @Min(value = 0, message = "wind speed can not be less then 0")
    @Max(value = 140, message = "maximum value of wind speed is 140")
    private Double windSpeed;

    @Column(name = "location_of_measurement")
    private String locationOfMeasurement;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    @JsonBackReference
    private Sensor sensor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getLocationOfMeasurement() {
        return locationOfMeasurement;
    }

    public void setLocationOfMeasurement(String locationOfMeasurement) {
        this.locationOfMeasurement = locationOfMeasurement;
    }
}
