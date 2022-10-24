package com.shakhner.sensorRestProject.weatherSensorRestProject.services;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MeasurementService {
    List<Measurement> getAllMeasurements();

    Optional<Measurement> getMeasurementById(int measurement_id);

    void saveMeasurement(Measurement measurement);

    void deleteMeasurementById(int measurement_id);

    Optional<Sensor> getSensor(int measurement_id);

    List<Measurement> getByLocationOfMeasurement(String location);

    List<Measurement> getDateByLocationBetween(String location, String from, String to);

    Integer getRainyDaysCountForLocation (String location);

    Integer getRainyDaysCountForSensorsName (String name);


}
