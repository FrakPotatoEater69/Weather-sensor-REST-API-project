package com.shakhner.sensorRestProject.weatherSensorRestProject.util;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        MeasurementDTO measurementDTO = new MeasurementDTO();
        measurementDTO.setSensor(measurement.getSensor());
        measurementDTO.setTimeOfMeasurement(measurement.getTimeOfMeasurement().toString());
        measurementDTO.setRaining(measurement.getRaining());
        measurementDTO.setWindSpeed(measurement.getWindSpeed());
        measurementDTO.setTemperatureValue(measurement.getTemperatureValue());

        return measurementDTO;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) throws ParseException {
        Measurement measurement = new Measurement();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        measurement.setTimeOfMeasurement(dateFormat.parse(measurementDTO.getTimeOfMeasurement()));

        measurement.setSensor(measurementDTO.getSensor());
        measurement.setRaining(measurementDTO.getRaining());
        measurement.setTemperatureValue(measurementDTO.getTemperatureValue());
        measurement.setWindSpeed(measurementDTO.getWindSpeed());
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
