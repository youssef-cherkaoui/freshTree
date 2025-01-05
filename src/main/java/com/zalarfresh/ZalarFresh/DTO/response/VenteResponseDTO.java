package com.zalarfresh.ZalarFresh.DTO.response;

import java.time.LocalDate;

public record VenteResponseDTO(
        Long id,
        String buyer,
        Double amount,
        LocalDate saleDate,
        ChampResponseDTO champ
) {
}
