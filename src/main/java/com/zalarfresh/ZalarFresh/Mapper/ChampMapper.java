package com.zalarfresh.ZalarFresh.Mapper;

import com.zalarfresh.ZalarFresh.DTO.request.ChampRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    @Mapping(target = "fermeId" , source = "ferme.id")
    ChampResponseDTO toResponseDTO(Champ champ);

    Champ toEntity(ChampRequestDTO champRequestDTO);

    List<ChampResponseDTO> toResponseDTOList(List<Champ> champ);
}
