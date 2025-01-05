package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.RecolteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.RecolteResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecolteService {

    RecolteResponseDTO creationRecolte(RecolteRequestDTO recolteDTO);

    RecolteResponseDTO updateRecolte( Long id,RecolteRequestDTO recolteDTO);

    RecolteResponseDTO deleteRecolte(Long id);

    List<RecolteResponseDTO> getRecoltes();
}
