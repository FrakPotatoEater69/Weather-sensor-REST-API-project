package com.shakhner.sensorRestProject.weatherSensorRestProject.util.validators;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
public class MeasurementValidator implements Validator {

    private DateFormat dateFormat;
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (measurementDTO.getSensor() == null) {
            errors.rejectValue("sensor", "", "Input sensor's name");
            return;
        }

        if (measurementDTO.getTimeOfMeasurement() == null || measurementDTO.getTimeOfMeasurement().isBlank()) {
            errors.rejectValue("timeOfMeasurement", "", "Input time of measurement");
            return;
        }

        Optional<Sensor> sensor = sensorService.getSensorByName(measurementDTO.getSensor().getName());


        if (sensor.isEmpty()) {
            errors.rejectValue("sensor", "", "There is no sensor with this name");
        }

        if (!isValid(measurementDTO)) {
            errors.rejectValue("timeOfMeasurement", "", "Invalid time of measurement, Input time of measurement if format: yyyy-MM-dd'T'HH:mm:ss. Example: 2014-03-12T13:37:27");
        }


    }

    private boolean isValid(MeasurementDTO measurementDTO) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setLenient(false);

        try {
            sdf.parse(measurementDTO.getTimeOfMeasurement());
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
