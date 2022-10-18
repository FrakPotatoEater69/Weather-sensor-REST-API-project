package com.shakhner.sensorRestProject.weatherSensorRestProject.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExceptionInfoCreator {
    public static String getInfo(BindingResult bindingResult){
        StringBuilder errors = new StringBuilder();
        List<FieldError> fields = bindingResult.getFieldErrors();
        for(FieldError fieldError : fields){
            errors.append(fieldError.getField()).append(" – ").append(fieldError.getDefaultMessage()).append(";");
        }
        return errors.toString();
    }
}
