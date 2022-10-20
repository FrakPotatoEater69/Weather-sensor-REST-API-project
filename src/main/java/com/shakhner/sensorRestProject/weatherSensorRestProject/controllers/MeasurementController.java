package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.MeasurementDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.Converter;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.ExceptionInfoCreator;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.MeasurementExceprions.MeasurementNotCreatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.Response;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.validators.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final Converter converter;
    private final MeasurementValidator measurementValidator;

    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, SensorService sensorService, Converter converter, MeasurementValidator measurementValidator) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.converter = converter;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){

        measurementValidator.validate(measurementDTO, bindingResult);

        if(bindingResult.hasErrors()){
            String errors = ExceptionInfoCreator.getInfo(bindingResult);

            throw new MeasurementNotCreatedException(errors);
        }

        measurementService.saveMeasurement(converter.convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(MeasurementNotCreatedException.class)
    private ResponseEntity<Response> measurementNotCreatedExceptionHandler(MeasurementNotCreatedException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




}
