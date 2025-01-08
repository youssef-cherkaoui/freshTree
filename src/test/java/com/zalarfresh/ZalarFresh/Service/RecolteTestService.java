package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.ArbreRecolteDetailsDTO;
import com.zalarfresh.ZalarFresh.DTO.request.RecolteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.RecolteResponseDTO;
import com.zalarfresh.ZalarFresh.Mapper.RecolteMapper;
import com.zalarfresh.ZalarFresh.Model.*;
import com.zalarfresh.ZalarFresh.Repository.ArbreRepository;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Repository.RecolteRepository;
import com.zalarfresh.ZalarFresh.Service.Impl.RecolteServiceImpl;
import jakarta.validation.constraints.PositiveOrZero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.zalarfresh.ZalarFresh.Model.Saison.PRINTEMPS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecolteTestService {



    @Mock
    private RecolteRepository recolteRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private RecolteMapper recolteMapper;

    @InjectMocks
    private RecolteServiceImpl recolteService;

    @Test
    void testCreationRecolte() {

        Champ champ = new Champ();
        champ.setId(1L);

        Arbre arbre = new Arbre();
        arbre.setId(1L);
        arbre.setChamp(champ);

        ArbreRecolteDetails details = new ArbreRecolteDetails();
        details.setArbre(arbre);
        details.setQuantite(50.0);

        RecolteRequestDTO recolteRequestDTO = new RecolteRequestDTO(
                PRINTEMPS,
                LocalDate.now(),
                1L,
                List.of(new ArbreRecolteDetailsDTO(1L))
        );

        Recolte recolte = new Recolte();
        recolte.setChamp(champ);
        recolte.setSaison(PRINTEMPS);

        Recolte savedRecolte = new Recolte();
        savedRecolte.setId(1L);
        savedRecolte.setChamp(champ);
        savedRecolte.setSaison(PRINTEMPS);
        savedRecolte.setQuantity(50.0);

        ChampResponseDTO champResponse = new ChampResponseDTO(1L, "Field A", 10.5, 1001L);
        ArbreResponseDTO arbreResponse = new ArbreResponseDTO(2L, LocalDate.of(2015, 3, 15), champResponse);

        RecolteResponseDTO recolteResponse = new RecolteResponseDTO(
                1L, PRINTEMPS, 1000.0, champResponse, arbreResponse
        );

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));

        when(recolteRepository.existsBySaisonAndChamp_Id(PRINTEMPS, 1L)).thenReturn(false);
        when(arbreRepository.findByChampId(1L)).thenReturn(List.of(arbre));
        when(recolteMapper.toEntity(recolteRequestDTO)).thenReturn(recolte);
        when(recolteRepository.save(recolte)).thenReturn(savedRecolte);
        when(recolteMapper.toResponseDto(savedRecolte)).thenReturn(recolteResponse);

        RecolteResponseDTO result = recolteService.creationRecolte(recolteRequestDTO);


        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(PRINTEMPS, result.season());
        assertEquals(1000.0, result.quantity());
        verify(recolteRepository, times(1)).save(recolte);
    }

}
