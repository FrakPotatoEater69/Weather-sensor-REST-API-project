package com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.MeasurementExceprions;

public class MeasurementNotFoundException extends RuntimeException{
    public MeasurementNotFoundException(String msg){
        super(msg);
    }
}
