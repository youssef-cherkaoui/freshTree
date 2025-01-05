package com.zalarfresh.ZalarFresh.DTO.request;

import java.time.LocalDate;

public record FermeCriteresRechercheDTO(
        String nom,
        String localisation,
        Double minSurface,
        Double maxSurface,
        LocalDate creationApres,
        LocalDate creationAvant


        ) {

}
