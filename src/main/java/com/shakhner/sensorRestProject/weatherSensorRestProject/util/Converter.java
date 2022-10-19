package com.shakhner.sensorRestProject.weatherSensorRestProject.util;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    private final ModelMapper modelMapper;

    @Autowired
    public Converter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }


    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
