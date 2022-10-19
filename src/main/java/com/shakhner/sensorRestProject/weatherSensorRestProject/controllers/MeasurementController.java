package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Measurement")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }


}
