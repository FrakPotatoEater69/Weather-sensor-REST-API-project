package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.exceptions.Response;
import com.shakhner.sensorRestProject.weatherSensorRestProject.exceptions.sensorExceptions.SensorNotCreatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.exceptions.sensorExceptions.SensorNotFoundException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.ExceptionInfoCreator;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.validators.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    public SensorController(MeasurementService measurementService, SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<SensorDTO> findAllSensors(){
        return sensorService.getAllSensors().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SensorDTO findOne(@PathVariable("id") int id){
        return convertToSensorDTO(sensorService.getSensorById(id).get());
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){

        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){

            throw new SensorNotCreatedException(ExceptionInfoCreator.getInfo(bindingResult));
        }

        sensorService.saveSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @PatchMapping
//    @RequestMapping("/updatelocation")
//    public ResponseEntity<HttpStatus>



    @ExceptionHandler(SensorNotCreatedException.class)
    private ResponseEntity<Response> sensorNotCreatedExceptionHandler(SensorNotCreatedException e){
        Response response = new Response(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SensorNotFoundException.class)
    private ResponseEntity<Response> sensorNotFoundExceptionHandler(SensorNotFoundException e){
        Response response = new Response("Sensor not found", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
