package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.ArbreRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArbreService {

    ArbreResponseDTO CreateArbre(ArbreRequestDTO arbreDTO);

    double calculateProductivity(Long id);

    List<ArbreResponseDTO> getAllArbres();

    ArbreResponseDTO deleteArbre(Long id);

    ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO arbreDTO);

}
