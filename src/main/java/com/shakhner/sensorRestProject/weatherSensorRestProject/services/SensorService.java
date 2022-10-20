package com.shakhner.sensorRestProject.weatherSensorRestProject.services;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorService {
    public List<Sensor> getAllSensors();

    public Optional<Sensor> getSensorById(int sensor_id);

    public void updateSensor(int id, Sensor sensor);

    public void saveSensor(Sensor sensor);

    public void deleteSensorById(int sensor_id);

    public List<Measurement> getMeasurementsList(int sensor_id);
    public List<Measurement> getMeasurementsList(String name);

    public Optional<Sensor> getSensorByName(String SensorName);

    public void changeLocation(String name, String newLocation);

}
