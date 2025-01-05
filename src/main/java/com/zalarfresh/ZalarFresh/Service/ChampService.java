package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.ChampRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChampService {

    ChampResponseDTO creationChamp(ChampRequestDTO champDTO);

    ChampResponseDTO getChampById(Long id);

    ChampResponseDTO updateChamp(Long id ,ChampRequestDTO champDTO);

    ChampResponseDTO deleteChamp(Long id);

    List<ChampResponseDTO> getAllChamps();
}
