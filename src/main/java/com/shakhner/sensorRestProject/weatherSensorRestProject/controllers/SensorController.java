package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.LocationWrapperDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers.SensorsDTOResponse;
import com.shakhner.sensorRestProject.weatherSensorRestProject.services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.Converter;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.ExceptionsResponse;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotCreatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.util.exceptions.sensorExceptions.SensorNotFoundException;
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
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;
    private final Converter converter;

    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper, Converter converter) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
        this.converter = converter;
    }

    @GetMapping
    @ResponseBody
    public SensorsDTOResponse findAllSensors() {
        return new SensorsDTOResponse(sensorService.getAllSensors().stream()
                .map(converter::convertToSensorDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SensorDTO findOneById(@PathVariable("id") int id) {
        return converter.convertToSensorDTO(sensorService.getSensorById(id).get());
    }

    @GetMapping("/findByName")
    @ResponseBody()
    public SensorDTO findOneByName(@RequestParam("name") String name) {
        return sensorService.getSensorByName(name).stream().map(converter::convertToSensorDTO)
                .findAny().orElseThrow(() -> new SensorNotFoundException());
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {

            throw new SensorNotCreatedException(ExceptionInfoCreator.getInfo(bindingResult));
        }

        sensorService.saveSensor(converter.convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping
    @RequestMapping("/{name}/updatelocation")
    public ResponseEntity<HttpStatus> updateLocation(@PathVariable("name") String name, @RequestBody @Valid LocationWrapperDTO locationWrapperDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SensorNotUpdatedException(ExceptionInfoCreator.getInfo(bindingResult));
        }

        sensorService.changeLocation(name, locationWrapperDTO.getLocation());
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/{name}/delete")
    private ResponseEntity<HttpStatus> deleteSensor(@PathVariable("name") String name) {
        sensorService.deleteSensorByName(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler(SensorNotCreatedException.class)
    private ResponseEntity<ExceptionsResponse> sensorNotCreatedExceptionHandler(SensorNotCreatedException e) {
        ExceptionsResponse response = new ExceptionsResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SensorNotFoundException.class)
    private ResponseEntity<ExceptionsResponse> sensorNotFoundExceptionHandler(SensorNotFoundException e) {
        ExceptionsResponse response = new ExceptionsResponse("Sensor not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorNotUpdatedException.class)
    private ResponseEntity<ExceptionsResponse> sensorNotUpdatedExceptionHandler(SensorNotUpdatedException e) {
        ExceptionsResponse response = new ExceptionsResponse(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    private ResponseEntity<ExceptionsResponse> numberFormatExceptionHandler(NumberFormatException e) {
        ExceptionsResponse response = new ExceptionsResponse("Input correct sensor id");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
