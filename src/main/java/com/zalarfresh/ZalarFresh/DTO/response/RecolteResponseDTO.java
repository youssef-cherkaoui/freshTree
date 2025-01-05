package com.zalarfresh.ZalarFresh.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecolteResponseDTO(
        Long id,
        String season,
        Double quantity,
        ChampResponseDTO champ,
        ArbreResponseDTO arbre
) {
}
