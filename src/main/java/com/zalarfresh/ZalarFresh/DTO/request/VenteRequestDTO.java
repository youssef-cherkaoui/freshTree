package com.zalarfresh.ZalarFresh.DTO.request;

import java.time.LocalDate;

public record VenteRequestDTO(
        double PrixUnique,
        double quantity,
        double revenue,
        LocalDate date,
        String client,
        Long RecolteId
) {
}
