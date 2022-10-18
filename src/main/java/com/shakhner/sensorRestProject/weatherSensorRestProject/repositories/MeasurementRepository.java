package com.shakhner.sensorRestProject.weatherSensorRestProject.repositories;

import com.shakhner.sensorRestProject.weatherSensorRestProject.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}