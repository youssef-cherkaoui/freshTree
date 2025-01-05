package com.zalarfresh.ZalarFresh.DTO.response;

import java.time.LocalDate;

public record ArbreResponseDTO(
        Long id,
        LocalDate plantingDate,
        ChampResponseDTO champ
) {
}
