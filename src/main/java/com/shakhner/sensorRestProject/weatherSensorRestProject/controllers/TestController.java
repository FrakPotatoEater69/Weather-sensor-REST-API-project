package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.ServicesImpl.MeasurementServiceImpl;
import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.ServicesImpl.SensorServiceImpl;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public TestController(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @GetMapping
    @ResponseBody
    public List<Measurement> getAllMeasurements(){
        return measurementService.getAllMeasurements();
    }
}
