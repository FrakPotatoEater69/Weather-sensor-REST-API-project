package com.shakhner.sensorRestProject.weatherSensorRestProject.util.validators;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if(measurementDTO.getSensor() == null){
            errors.rejectValue("sensor", "", "Input sensor's name");
            return;
        }

        Optional<Sensor> sensor = sensorService.getSensorByName(measurementDTO.getSensor().getName());


        if(sensor.isEmpty()){
            errors.rejectValue("sensor", "", "There is no sensor with this name");
        }

    }
}
