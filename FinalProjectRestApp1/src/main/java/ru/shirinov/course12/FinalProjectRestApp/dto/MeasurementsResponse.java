package ru.shirinov.course12.FinalProjectRestApp.dto;

import java.util.List;
import lombok.Data;

@Data
public class MeasurementsResponse {
    private List<MeasurementDTO> measurementDTO;

    public MeasurementsResponse(List<MeasurementDTO> measurementDTO) {
        this.measurementDTO = measurementDTO;
    }
}
