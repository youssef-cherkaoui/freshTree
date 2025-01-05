package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FermeRepositoryCustom {

    List<Ferme> searchFerme(FermeCriteresRechercheDTO critereRechercheFerme);
}
