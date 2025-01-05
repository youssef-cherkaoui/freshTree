package com.zalarfresh.ZalarFresh.DTO.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChampRequestDTO(
        @NotBlank(message = "Le nom du champ ne peut pas être vide") String nom,
        @DecimalMin(value = "0.1", message = "La surface doit être d'au moins 0,1 hectare") double surface,
        @NotNull(message = "L'identifiant de la ferme est obligatoire") Long fermeId
) {
}
