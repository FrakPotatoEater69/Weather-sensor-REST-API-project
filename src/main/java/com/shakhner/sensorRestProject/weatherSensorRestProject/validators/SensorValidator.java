package com.shakhner.sensorRestProject.weatherSensorRestProject.validators;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SensorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO
    }
}
