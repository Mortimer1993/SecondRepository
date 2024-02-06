package ru.shirinov.course12.FinalProjectRestApp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class SensorDTO {
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 30, message = "Name must not be less than 3 or more than 30 characters")
    private String name;


    @JsonCreator
    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
