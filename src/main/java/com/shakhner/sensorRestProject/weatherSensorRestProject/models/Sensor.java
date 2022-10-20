package com.shakhner.sensorRestProject.weatherSensorRestProject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name of sensor should not be empty")
    @Size(min = 3, max = 30, message = "sensor name should be between 3 and 30 characters")
    private String name;

    @Column(name = "location")
    @Size(min = 3, max = 30, message = "location name should be between 3 and 30 characters")
    @NotEmpty(message = "name of location should not be empty")
    private String location;


    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private List<Measurement> measurements;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
