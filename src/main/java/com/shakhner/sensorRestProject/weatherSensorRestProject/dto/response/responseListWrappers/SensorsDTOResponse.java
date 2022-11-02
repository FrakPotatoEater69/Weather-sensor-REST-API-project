package com.shakhner.sensorRestProject.weatherSensorRestProject.dto.response.responseListWrappers;

import com.shakhner.sensorRestProject.weatherSensorRestProject.dto.SensorDTO;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "Response when it should store the list of SensorsDTOResponse objects")
public class SensorsDTOResponse {
    private List<SensorDTO> sensors;

    public SensorsDTOResponse(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }

    public SensorsDTOResponse(){}

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }
}
