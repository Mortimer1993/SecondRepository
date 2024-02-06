package ru.shirinov.course12.FinalProjectRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shirinov.course12.FinalProjectRestApp.models.Sensor;

import java.util.Optional;

public interface SensorRepositories extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
