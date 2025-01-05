package com.zalarfresh.ZalarFresh.DTO.response;

import com.zalarfresh.ZalarFresh.DTO.embarquee.ChampDTO;

import java.util.List;

public record FermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        Double surface,
        List<ChampDTO> champ
) {
}
