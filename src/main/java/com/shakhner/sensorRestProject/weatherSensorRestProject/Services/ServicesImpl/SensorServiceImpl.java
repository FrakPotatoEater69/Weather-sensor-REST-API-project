package com.shakhner.sensorRestProject.weatherSensorRestProject.Services.ServicesImpl;

import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.SensorService;
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
        return sensorRepository.findById(sensor_id);
        //TODO Throw exception, if not found
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
        sensorRepository.deleteById(sensor_id);
    }

    @Override
    public List<Measurement> getMeasurementsList(int sensor_id) {
        Optional <Sensor> sensor = sensorRepository.findById(sensor_id);

        if(sensor.isPresent()){
            Hibernate.initialize(sensor.get().getMeasurements());
            return sensor.get().getMeasurements();
        }

        //TODO throw exception
        return null;
    }

    @Override
    public List<Sensor> getPageableAndSortedSensorList(Integer page, Integer SensorPerPage, Boolean sortByDate) {
        //TODO
        return null;
    }
}
