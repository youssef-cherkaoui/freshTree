package com.zalarfresh.ZalarFresh.Mapper;


import com.zalarfresh.ZalarFresh.DTO.embarquee.ChampDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.FermeResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeResponseDTO toResponseDTO(Ferme ferme);

    Ferme toEntity(FermeRequestDTO fermeRequestDTO);

    //List<ChampDTO> toChampDTOList(List<Champ> champ);


    default List<ChampDTO> toChampDTOList(List<Champ> champs) {
        if (champs == null) {
            return null;
        }
        return champs.stream()
                .map(champ -> new ChampDTO(champ.getId(), champ.getNom(), champ.getSurface()))
                .toList();
    }
}
