package ru.shirinov.course12.FinalProjectRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shirinov.course12.FinalProjectRestApp.models.Measurement;

import java.util.Optional;

public interface MeasurementRepositories extends JpaRepository<Measurement, Integer> {

}
