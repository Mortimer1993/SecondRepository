package ru.shirinov.course12.FinalProjectRestApp.dto;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementDTO> measurementDTO;

    public MeasurementsResponse(List<MeasurementDTO> measurementDTO) {
        this.measurementDTO = measurementDTO;
    }

    public List<MeasurementDTO> getMeasurementDTO() {
        return measurementDTO;
    }

    public void setMeasurementDTO(List<MeasurementDTO> measurementDTO) {
        this.measurementDTO = measurementDTO;
    }
}
