package com.shakhner.sensorRestProject.weatherSensorRestProject.services.ServicesImpl;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.repositories.MeasurementRepository;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.MeasurementExceprions.MeasurementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public void saveMeasurement(Measurement measurement) {
        Optional<Sensor> sensor = sensorService.getSensorByName(measurement.getSensor().getName());

        measurement.setSensor(sensor.get());
        sensor.get().getMeasurements().add(measurement);

        measurement.setLocationOfMeasurement(sensor.get().getLocation());

        measurementRepository.save(measurement);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMeasurementById(int measurement_id) {
        measurementRepository.deleteById(measurement_id);
    }

    @Override
    public Optional<Sensor> getSensor(int measurement_id) {
        Measurement measurement = measurementRepository.findById(measurement_id).get();
        return Optional.ofNullable(measurement.getSensor());
    }


    @Override
    public List<Measurement> getByLocationOfMeasurement(String location) {
        List<Measurement> measurements = measurementRepository.findByLocationOfMeasurement(location);

        if (measurements.isEmpty()) {
            throw new MeasurementNotFoundException("There is no measurements in this location or location not found");
        }

        return measurements;
    }

    @Override
    public List<Measurement> getDateByLocationBetween(String location, String from, String to) {
        List<Measurement> measurements = getByLocationOfMeasurement(location);

        if (to.isBlank() || from.isBlank())
            throw new MeasurementNotFoundException("Input from and to dates value");
        Date dateFrom = convertToDate(from);
        Date dateTo = convertToDate(to);


        if (measurements.isEmpty()) {
            throw new MeasurementNotFoundException("There is no measurements in this location between this dates");
        }

        List<Measurement> necessaryMeasurements = new ArrayList<>();

        for (Measurement measurement : measurements) {
            if (measurement.getTimeOfMeasurement().after(dateFrom) && measurement.getTimeOfMeasurement().before(dateTo))
                necessaryMeasurements.add(measurement);
        }


        if (necessaryMeasurements.isEmpty()) {
            throw new MeasurementNotFoundException("There is no measurements in this location between this dates");
        }

        necessaryMeasurements.sort(Comparator.naturalOrder());

        return necessaryMeasurements;
    }

    private Date convertToDate(String date) {

        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        pattern.setLenient(false);
        Date validDate;
        try {
            validDate = pattern.parse(date);
        } catch (ParseException e) {
            throw new MeasurementNotFoundException("Invalid date type, example: 2017-12-02T17:23:27");
        }

        return validDate;
    }


}
