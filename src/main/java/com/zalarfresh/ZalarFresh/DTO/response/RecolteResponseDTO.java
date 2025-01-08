package com.zalarfresh.ZalarFresh.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zalarfresh.ZalarFresh.Model.Saison;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecolteResponseDTO(
        Long id,
        Saison season,
        Double quantity,
        ChampResponseDTO champ,
        ArbreResponseDTO arbre
) {
}
