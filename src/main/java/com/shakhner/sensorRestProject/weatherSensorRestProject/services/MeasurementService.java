package com.shakhner.sensorRestProject.weatherSensorRestProject.services;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;

import java.util.List;
import java.util.Optional;

public interface MeasurementService {
    public List<Measurement> getAllMeasurements();

    public Optional<Measurement> getMeasurementById(int measurement_id);

    public void updateMeasurement(int id, Measurement measurement);

    public void saveMeasurement(Measurement measurement);

    public void deleteMeasurementById(int measurement_id);

    public Optional<Sensor> getSensor(int measurement_id);

    public List<Measurement> getPageableAndSortedMeasurementList(Integer page, Integer MeasurementPerPage, Boolean sortByDate);
}
