package com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions;

public class SensorNotUpdatedException extends RuntimeException{
    public SensorNotUpdatedException(String msg){
        super(msg);
    }
}
