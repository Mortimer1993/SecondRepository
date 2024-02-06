package ru.shirinov.course12.FinalProjectRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shirinov.course12.FinalProjectRestApp.models.Measurement;

public interface MeasurementRepositories extends JpaRepository<Measurement, Integer> {

}
