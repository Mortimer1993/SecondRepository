package ru.shirinov.course12.FinalProjectRestApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDTO {
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 30, message = "Name must not be less than 3 or more than 30 characters")
    private String name;


}
