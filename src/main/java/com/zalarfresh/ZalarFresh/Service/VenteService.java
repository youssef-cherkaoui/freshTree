package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.VenteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.VenteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenteService {

    VenteResponseDTO creationVente(VenteRequestDTO venteDTO);

    List<VenteResponseDTO> GetAllVentes();

    VenteResponseDTO updateVente(Long id ,VenteRequestDTO venteDTO);

    void deleteVente(Long id);
}
