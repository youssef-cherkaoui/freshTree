package com.zalarfresh.ZalarFresh.Mapper;

import com.zalarfresh.ZalarFresh.DTO.request.ArbreRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Arbre;
import com.zalarfresh.ZalarFresh.Model.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    @Mapping(source = "champ", target = "champ")
    ArbreResponseDTO ArbreToArbreResponseDTO(Arbre arbre);

    @Mapping(source = "champId" , target = "champ.id")
    Arbre toEntity(ArbreRequestDTO arbreRequestDTO);

    @Mapping(target = "fermeId", source ="ferme.id")
    ChampResponseDTO toChampResponseDTO(Champ champ);
}
