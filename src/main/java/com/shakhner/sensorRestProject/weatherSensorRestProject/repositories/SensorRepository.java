package com.shakhner.sensorRestProject.weatherSensorRestProject.repositories;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);

    void deleteSensorByName(String name);
}
