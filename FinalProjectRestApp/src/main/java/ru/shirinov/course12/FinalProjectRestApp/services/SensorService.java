package ru.shirinov.course12.FinalProjectRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shirinov.course12.FinalProjectRestApp.models.Sensor;
import ru.shirinov.course12.FinalProjectRestApp.repositories.SensorRepositories;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepositories sensorRepositories;

    @Autowired
    public SensorService(SensorRepositories sensorRepositories) {
        this.sensorRepositories = sensorRepositories;
    }

    public Optional<Sensor> findByName(String sensorName) {
        return sensorRepositories.findByName(sensorName);
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorRepositories.save(sensor);
    }

}
