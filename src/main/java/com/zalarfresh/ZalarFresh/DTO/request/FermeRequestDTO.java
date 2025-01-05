package com.zalarfresh.ZalarFresh.DTO.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record FermeRequestDTO(
        @NotBlank(message = "Farm name cannot be blank") String nom ,
        @NotBlank(message = "Localization cannot be blank") String localisation,
        @DecimalMin(value = "1.0", message = "Farm surface must be greater than 1.0 hectares") double surface,
        @PastOrPresent(message = "Creation date must be in the past or present") LocalDate creationDate

) {

}
