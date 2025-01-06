package com.zalarfresh.ZalarFresh.DTO.response;

import com.zalarfresh.ZalarFresh.DTO.embarquee.ChampDTO;

import java.time.LocalDate;
import java.util.List;

public record FermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        Double surface,
        LocalDate creationDate,
        List<ChampDTO> champ
) {
}
