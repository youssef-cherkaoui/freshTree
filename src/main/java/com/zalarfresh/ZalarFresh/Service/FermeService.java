package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.FermeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FermeService {

    FermeResponseDTO creationFerme(FermeRequestDTO fermeDTO);

    Page<FermeResponseDTO> getAllFermes(Pageable pageable);

    FermeResponseDTO getFermeById(Long id);

    FermeResponseDTO updateFerme(Long id,FermeRequestDTO fermeDTO);

    void deleteFerme(Long id);

    List<FermeResponseDTO> searchFerme(FermeCriteresRechercheDTO critere);


}
