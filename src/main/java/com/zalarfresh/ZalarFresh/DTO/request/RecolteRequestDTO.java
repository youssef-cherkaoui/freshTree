package com.zalarfresh.ZalarFresh.DTO.request;

import com.zalarfresh.ZalarFresh.Model.Saison;

import java.time.LocalDate;
import java.util.List;

public record RecolteRequestDTO(
        Saison saison,
        LocalDate date,
        Long champId,
        List<ArbreRecolteDetailsDTO> arbreRecolteDetails
) {
}
