package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.ChampRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Mapper.ChampMapper;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Repository.FermeRepository;
import com.zalarfresh.ZalarFresh.Service.Impl.ChampServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ChampTestService {

    @Mock
    private ChampMapper champMapper;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private FermeRepository fermeRepository;

    @InjectMocks
    private ChampServiceImpl champService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void CreationChamp() {

        ChampRequestDTO requestDTO = new ChampRequestDTO("nom", 4, 5L);
        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setNom("Ferme 1");
        ferme.setLocalisation("Localisation");
        ferme.setSurface(10.0);
        ferme.setCreationDate(LocalDate.of(2020, 1, 1));
        ferme.setChamp(new ArrayList<>());

        List<Recolte> recoltes = new ArrayList<>();

        Champ champ = new Champ(1L, "Champ 1", 0.5, ferme , recoltes);
        ChampResponseDTO responseDTO = new ChampResponseDTO(5L, "Champ 1", 50.0, 5L);

        when(fermeRepository.findById(requestDTO.fermeId())).thenReturn(Optional.of(ferme));
        when(champMapper.toEntity(requestDTO)).thenReturn(champ);
        when(champRepository.save(champ)).thenReturn(champ);
        when(champMapper.toResponseDTO(champ)).thenReturn(responseDTO);

        ChampResponseDTO result = champService.creationChamp(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
    }


    @Test
    void testUpdateChamp() {
        // Arrange
        Long champId = 1L;
        ChampRequestDTO champDTO = new ChampRequestDTO("Updated Champ", 5.0, 1L);

        Ferme ferme = new Ferme(1L, "Ferme 1", "Localisation", 10.0, LocalDate.of(2020, 1, 1), new ArrayList<>());
        Champ champ = new Champ(1L, "Champ 1", 0.5, ferme, new ArrayList<>());

        Champ updatedChamp = new Champ(1L, "Updated Champ", 5.0, ferme, new ArrayList<>());
        ChampResponseDTO responseDTO = new ChampResponseDTO(1L, "Updated Champ", 5.0, 1L);

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(fermeRepository.findById(champDTO.fermeId())).thenReturn(Optional.of(ferme));
        when(champMapper.toEntity(champDTO)).thenReturn(updatedChamp);
        when(champRepository.save(updatedChamp)).thenReturn(updatedChamp);
        when(champMapper.toResponseDTO(updatedChamp)).thenReturn(responseDTO);

        // Act
        ChampResponseDTO result = champService.updateChamp(champId, champDTO);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO, result);
    }

    @Test
    void testDeleteChamp() {

        Long champId = 1L;

        Ferme ferme = new Ferme(1L, "Ferme 1", "Localisation", 10.0, LocalDate.of(2020, 1, 1), new ArrayList<>());
        Champ champ = new Champ(1L, "Champ 1", 0.5, ferme, new ArrayList<>());
        ChampResponseDTO responseDTO = new ChampResponseDTO(1L, "Champ 1", 0.5, 1L);

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        doNothing().when(champRepository).delete(champ); // Assuming the method returns void or no result
        when(champMapper.toResponseDTO(champ)).thenReturn(responseDTO);

        ChampResponseDTO result = champService.deleteChamp(champId);

        assertNotNull(result);
        assertEquals(responseDTO, result);
    }



    @Test
    void testGetAllChamps() {
        // Arrange
        Ferme ferme = new Ferme(1L, "Ferme 1", "Localisation", 10.0, LocalDate.of(2020, 1, 1), new ArrayList<>());
        Champ champ1 = new Champ(1L, "Champ 1", 0.5, ferme, new ArrayList<>());
        Champ champ2 = new Champ(2L, "Champ 2", 1.0, ferme, new ArrayList<>());

        List<Champ> champs = Arrays.asList(champ1, champ2);
        List<ChampResponseDTO> responseDTOs = Arrays.asList(new ChampResponseDTO(1L, "Champ 1", 0.5, 1L),
                new ChampResponseDTO(2L, "Champ 2", 1.0, 1L));

        // Mocking repository methods
        when(champRepository.findAll()).thenReturn(champs);
        when(champMapper.toResponseDTOList(champs)).thenReturn(responseDTOs);

        // Act
        List<ChampResponseDTO> result = champService.getAllChamps();

        // Assert
        assertNotNull(result);
        assertEquals(responseDTOs, result);
    }


}
