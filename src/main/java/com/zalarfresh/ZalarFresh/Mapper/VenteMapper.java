package com.zalarfresh.ZalarFresh.Mapper;

import com.zalarfresh.ZalarFresh.DTO.request.VenteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.VenteResponseDTO;
import com.zalarfresh.ZalarFresh.Model.Vente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    VenteResponseDTO toResponseDTO(Vente vente);

    Vente toEntity(VenteRequestDTO venteRequestDTO);
}
