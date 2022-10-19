package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.LocationWrapperDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.Response;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotCreatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotFoundException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.ExceptionInfoCreator;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotUpdatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.validators.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @PatchMapping
    @RequestMapping("/{id}/updatelocation")
    public ResponseEntity<HttpStatus> updateLocation(@PathVariable("id") int id, @RequestBody @Valid LocationWrapperDTO locationWrapperDTO,
                                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SensorNotUpdatedException(ExceptionInfoCreator.getInfo(bindingResult));
        }

        sensorService.changeLocation(id, locationWrapperDTO.getLocation());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<HttpStatus> deleteSensor(@PathVariable("id") int id){
        sensorService.deleteSensorById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



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

    @ExceptionHandler(SensorNotUpdatedException.class)
    private ResponseEntity<Response> sensorNotUpdatedExceptionHandler(SensorNotUpdatedException e){
        Response response = new Response(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
