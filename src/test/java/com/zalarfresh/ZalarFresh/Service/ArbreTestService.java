package com.zalarfresh.ZalarFresh.Service;


import com.zalarfresh.ZalarFresh.DTO.request.ArbreRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.ValidationException;
import com.zalarfresh.ZalarFresh.Mapper.ArbreMapper;
import com.zalarfresh.ZalarFresh.Model.Arbre;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Repository.ArbreRepository;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Service.Impl.ArbreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArbreTestService {

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreMapper arbreMapper;

    @InjectMocks
    private ArbreServiceImpl arbreService;



    @Test
    void testCreateArbre() {

        Champ champ = new Champ();
        champ.setId(1L);
        champ.setNom("Champ Test");
        champ.setSurface(10.0);
        champ.setFerme(new Ferme());

        ChampResponseDTO champResponseDTO = new ChampResponseDTO(
                champ.getId(),
                champ.getNom(),
                champ.getSurface(),
                champ.getFerme().getId()
        );


        ArbreRequestDTO arbreRequestDTO = new ArbreRequestDTO(
                LocalDate.now(),
                1L
        );

        ArbreResponseDTO responseDTO = new ArbreResponseDTO(
                1L,
                LocalDate.now(),
                champResponseDTO
        );

        Arbre arbre = new Arbre();
        arbre.setChamp(champ);

        Arbre savedArbre = new Arbre();
        savedArbre.setId(1L);
        savedArbre.setChamp(champ);

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));
        when(arbreRepository.countByChampId(1L)).thenReturn(0L);
        when(arbreMapper.toEntity(arbreRequestDTO)).thenReturn(arbre);
        when(arbreRepository.save(arbre)).thenReturn(savedArbre);
        when(arbreMapper.ArbreToArbreResponseDTO(savedArbre)).thenReturn(responseDTO);

        ArbreResponseDTO result = arbreService.CreateArbre(arbreRequestDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Champ Test", result.champ().nom());
        verify(champRepository, times(1)).findById(1L);
        verify(arbreRepository, times(1)).save(arbre);
    }


    @Test
    void testCreateArbre_ThrowsValidationException() {
        // Arrange
        Champ champ = new Champ();
        champ.setId(1L);
        champ.setSurface(1.0);

        ArbreRequestDTO arbreRequestDTO = new ArbreRequestDTO(
                LocalDate.now(),
                1L
        );

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));
        when(arbreRepository.countByChampId(1L)).thenReturn(100L);

        assertThrows(ValidationException.class, () -> arbreService.CreateArbre(arbreRequestDTO));
        verify(champRepository, times(1)).findById(1L);
        verify(arbreRepository, never()).save(any(Arbre.class));
    }

    @Test
    void testUpdateArbre() {
        Long arbreId = 1L;

        Champ champ = new Champ();
        champ.setId(2L);
        champ.setSurface(10.0);

        Arbre existingArbre = new Arbre();
        existingArbre.setId(arbreId);
        existingArbre.setPlantingDate(LocalDate.of(2021, 1, 1));

        ArbreRequestDTO arbreRequestDTO = new ArbreRequestDTO(
                LocalDate.of(2022, 5, 15),
                champ.getId()
        );

        Arbre updatedArbre = new Arbre();
        updatedArbre.setId(arbreId);
        updatedArbre.setPlantingDate(LocalDate.of(2022, 5, 15));
        updatedArbre.setChamp(champ);

        ArbreResponseDTO arbreResponseDTO = new ArbreResponseDTO(
                arbreId,
                LocalDate.of(2022, 5, 15),
                new ChampResponseDTO(champ.getId(), "Champ Test", 10.0, null)
        );

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(existingArbre));
        when(champRepository.findById(champ.getId())).thenReturn(Optional.of(champ));
        when(arbreRepository.save(existingArbre)).thenReturn(updatedArbre);
        when(arbreMapper.ArbreToArbreResponseDTO(updatedArbre)).thenReturn(arbreResponseDTO);

        ArbreResponseDTO result = arbreService.updateArbre(arbreId, arbreRequestDTO);

        assertNotNull(result);
        assertEquals(arbreId, result.id());
        assertEquals(LocalDate.of(2022, 5, 15), result.plantingDate());
        assertEquals(champ.getId(), result.champ().id());
        verify(arbreRepository, times(1)).findById(arbreId);
        verify(arbreRepository, times(1)).save(existingArbre);
    }


    @Test
    void testDeleteArbre() {
        Long arbreId = 1L;

        Arbre arbre = new Arbre();
        arbre.setId(arbreId);

        ArbreResponseDTO arbreResponseDTO = new ArbreResponseDTO(
                arbreId,
                LocalDate.of(2021, 1, 1),
                new ChampResponseDTO(2L, "Champ Test", 10.0, null)
        );

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));
        when(arbreMapper.ArbreToArbreResponseDTO(arbre)).thenReturn(arbreResponseDTO);

        ArbreResponseDTO result = arbreService.deleteArbre(arbreId);

        assertNotNull(result);
        assertEquals(arbreId, result.id());
        verify(arbreRepository, times(1)).findById(arbreId);
        verify(arbreRepository, times(1)).delete(arbre);
    }


    @Test
    void testGetAllArbres() {
        List<Arbre> arbres = List.of(
                new Arbre(1L, LocalDate.of(2021, 1, 1), null),
                new Arbre(2L, LocalDate.of(2020, 5, 15), null)
        );

        List<ArbreResponseDTO> arbreResponseDTOS = List.of(
                new ArbreResponseDTO(1L, LocalDate.of(2021, 1, 1), null),
                new ArbreResponseDTO(2L, LocalDate.of(2020, 5, 15), null)
        );

        when(arbreRepository.findAll()).thenReturn(arbres);
        when(arbreMapper.ArbreToArbreResponseDTO(any(Arbre.class)))
                .thenAnswer(invocation -> {
                    Arbre arbre = invocation.getArgument(0);
                    return new ArbreResponseDTO(arbre.getId(), arbre.getPlantingDate(), null);
                });

        List<ArbreResponseDTO> result = arbreService.getAllArbres();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(2L, result.get(1).id());
        verify(arbreRepository, times(1)).findAll();
    }


}
