package ru.shirinov.course12.FinalProjectRestApp.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDTO {
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;
    @NotNull
    private Boolean raining;
    @NotNull
    private SensorDTO sensor;

}
