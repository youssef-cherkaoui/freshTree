package com.zalarfresh.ZalarFresh.Mapper;

import com.zalarfresh.ZalarFresh.DTO.request.RecolteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.RecolteResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    @Mapping(source = "champ.ferme.id", target = "champ.fermeId")
    @Mapping(source = "saison", target = "season")
    RecolteResponseDTO toResponseDto(Recolte recolte);

    Recolte toEntity(RecolteRequestDTO recolteRequestDTO);
}
