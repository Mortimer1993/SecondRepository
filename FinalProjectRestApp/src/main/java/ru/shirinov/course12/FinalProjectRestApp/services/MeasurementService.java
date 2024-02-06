package ru.shirinov.course12.FinalProjectRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirinov.course12.FinalProjectRestApp.models.Measurement;
import ru.shirinov.course12.FinalProjectRestApp.models.Sensor;
import ru.shirinov.course12.FinalProjectRestApp.repositories.MeasurementRepositories;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories, SensorService sensorService) {
        this.measurementRepositories = measurementRepositories;
        this.sensorService = sensorService;
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrich(measurement);
        measurementRepositories.save(measurement);
    }

    private void enrich(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }

    public List<Measurement> findAll() {
        return measurementRepositories.findAll();
    }

}
