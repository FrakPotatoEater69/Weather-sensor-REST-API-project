package com.shakhner.sensorRestProject.weatherSensorRestProject.services.ServicesImpl;

import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotFoundException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.repositories.SensorRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Optional<Sensor> getSensorById(int sensor_id) {
        Optional<Sensor> sensor = sensorRepository.findById(sensor_id);

        if (sensor.isEmpty())
            throw new SensorNotFoundException();

        return sensor;
    }

    @Override
    @Transactional(readOnly = false)
    public void updateSensor(int id, Sensor sensor) {
        sensor.setId(id);
        sensorRepository.save(sensor);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSensorById(int sensor_id) {
        getSensorById(sensor_id);
        sensorRepository.deleteById(sensor_id);
    }

    @Override
    public List<Measurement> getMeasurementsList(int sensor_id) {
        Optional<Sensor> sensor = getSensorById(sensor_id);

        return sensor.get().getMeasurements();

    }

    @Override
    public List<Measurement> getMeasurementsList(String name) {
        Optional<Sensor> sensor = getSensorByName(name);

        return sensor.get().getMeasurements();

    }


    @Override
    public Optional<Sensor> getSensorByName(String sensorName) {
        Optional<Sensor> sensor = sensorRepository.findByName(sensorName);
        if (sensor.isEmpty())
            throw new SensorNotFoundException();
        return sensor;
    }

    @Transactional(readOnly = false)
    public void changeLocation(String name, String newLocation) {
        Optional<Sensor> sensor = getSensorByName(name);
        sensor.get().setLocation(newLocation);
    }

}
