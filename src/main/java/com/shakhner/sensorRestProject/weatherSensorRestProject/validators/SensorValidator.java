package com.shakhner.sensorRestProject.weatherSensorRestProject.validators;

import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String sensorName = ((Sensor) target).getName();

        if(sensorService.getSensorByName(sensorName).isPresent()){
            errors.rejectValue("name", "", "Sensor with this name is already exist");
        }
    }
}
