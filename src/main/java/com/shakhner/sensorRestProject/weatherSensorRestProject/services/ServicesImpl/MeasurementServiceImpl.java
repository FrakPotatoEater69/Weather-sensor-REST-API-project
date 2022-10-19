package com.shakhner.sensorRestProject.weatherSensorRestProject.services.ServicesImpl;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.repositories.MeasurementRepository;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;


    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Override
    public List<Measurement> getAllMeasurements() {
        System.out.println(measurementRepository.findAll());
        return measurementRepository.findAll();
    }

    @Override
    public Optional<Measurement> getMeasurementById(int measurement_id) {
        return measurementRepository.findById(measurement_id);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateMeasurement(int id, Measurement measurement) {
        measurement.setId(id);
        measurementRepository.save(measurement);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveMeasurement(Measurement measurement) {
        Optional<Sensor> sensor = sensorService.getSensorByName(measurement.getSensor().getName());

        measurement.setSensor(sensor.get());
        sensor.get().getMeasurements().add(measurement);

        measurement.setLocationOfMeasurement(sensor.get().getLocation());
        //CHECK!!!!!!!!!!!!!!!
        measurementRepository.save(measurement);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMeasurementById(int measurement_id) {
        measurementRepository.deleteById(measurement_id);
    }

    @Override
    public Optional<Sensor> getSensor(int measurement_id) {
        Measurement measurement = measurementRepository.findById(measurement_id).get()  ;
        return Optional.ofNullable(measurement.getSensor());
    }

    @Override
    public List<Measurement> getPageableAndSortedMeasurementList(Integer page, Integer MeasurementPerPage, Boolean sortByDate) {
        //TODO
        return null;
    }
}
