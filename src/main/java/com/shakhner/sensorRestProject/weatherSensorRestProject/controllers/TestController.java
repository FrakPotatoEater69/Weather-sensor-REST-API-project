package com.shakhner.sensorRestProject.weatherSensorRestProject.controllers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.MeasurementService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.Services.SensorService;
import com.shakhner.sensorRestProject.weatherSensorRestProject.exceptions.Response;
import com.shakhner.sensorRestProject.weatherSensorRestProject.exceptions.sensorExceptions.SensorNotCreatedException;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import com.shakhner.sensorRestProject.weatherSensorRestProject.validators.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public TestController(MeasurementService measurementService, SensorService sensorService, SensorValidator sensorValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    @ResponseBody
    public List<Measurement> getAllMeasurements(){
        return measurementService.getAllMeasurements();
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid Sensor sensor, BindingResult bindingResult){

        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();
            List<FieldError> fields = bindingResult.getFieldErrors();
            for(FieldError fieldError : fields){
                errors.append(fieldError.getField()).append(" – ").append(fieldError.getDefaultMessage()).append(";");
            }

            throw new SensorNotCreatedException(errors.toString());
        }

        sensorService.saveSensor(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(SensorNotCreatedException.class)
    private ResponseEntity<Response> sensorNotCreatedExceptionHandler(SensorNotCreatedException e){
        Response response = new Response(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
