package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.embarquee.ChampDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.FermeResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Mapper.FermeMapper;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Repository.FermeRepository;
import com.zalarfresh.ZalarFresh.Service.Impl.FermeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FermeTestService {


    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeServiceImpl fermeService;

    private Ferme ferme ;
    private FermeRequestDTO fermeRequestDTO;
    private FermeResponseDTO fermeResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ChampDTO champDTO = new ChampDTO(1L, "Champ1", 50.0);
        List<ChampDTO> champ = List.of(champDTO);
        ferme = new Ferme();
        ferme.setId(1L);
        ferme.setNom("Ferme1");
        ferme.setLocalisation("Localisation 1 ");
        ferme.setSurface(100.0);
        ferme.setCreationDate(LocalDate.of(2024, 1,1));

        fermeRequestDTO = new FermeRequestDTO(
                "Ferme 1",
                "Localisation 1",
                100.0,
                LocalDate.of(2024,1,1));

        fermeResponseDTO = new FermeResponseDTO(
                1L,
                "Ferme 1",
                "Localisation 1",
                100.0,
                LocalDate.of(2024,1,1),
                champ);

    }

    @Test
    void TestCreationFerme(){
        when(fermeMapper.toEntity(fermeRequestDTO)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);

        FermeResponseDTO response = fermeService.creationFerme(fermeRequestDTO);
        assertNotNull(response);
        assertEquals(fermeResponseDTO , response);
        verify(fermeRepository , times(1)).save(ferme);
    }

    @Test
    void testGetAllFermes() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Ferme> fermes = Arrays.asList(ferme);
        Page<Ferme> page = new PageImpl<>(fermes);

        when(fermeRepository.findAll(pageRequest)).thenReturn(page);
        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);

        Page<FermeResponseDTO> response = fermeService.getAllFermes(pageRequest);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(fermeResponseDTO, response.getContent().get(0));
    }

    @Test
    void testGetFermeById_Found() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);

        FermeResponseDTO response = fermeService.getFermeById(1L);

        assertNotNull(response);
        assertEquals(fermeResponseDTO, response);
    }

    @Test
    void testGetFermeById_NotFound() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> fermeService.getFermeById(1L));
    }

    @Test
    void testUpdateFerme() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);

        FermeResponseDTO response = fermeService.updateFerme(1L, fermeRequestDTO);

        assertNotNull(response);
        assertEquals(fermeResponseDTO, response);
        verify(fermeRepository, times(1)).save(ferme);
    }

    @Test
    void testUpdateFerme_NotFound() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> fermeService.updateFerme(1L, fermeRequestDTO));
    }

    @Test
    void testDeleteFerme_Found() {
        when(fermeRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> fermeService.deleteFerme(1L));
        verify(fermeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFerme_NotFound() {
        when(fermeRepository.existsById(1L)).thenReturn(false);

        assertThrows(CustomNotFoundException.class, () -> fermeService.deleteFerme(1L));
    }

    @Test
    void testSearchFerme() {
        FermeCriteresRechercheDTO critere = new FermeCriteresRechercheDTO("Ferme",
                "Localisation",
                100.0,
                null,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2022, 1, 1));
        Ferme ferme = new Ferme();
        List<ChampDTO> champs = Arrays.asList(new ChampDTO(1L, "Champ1", 50.0));
        FermeResponseDTO fermeResponseDTO = new FermeResponseDTO(
                1L,
                "Ferme",
                "Localisation",
                150.0,
                LocalDate.of(2021, 6, 15), // creationDate
                champs
        );
        List<Ferme> fermes = Arrays.asList(ferme);

        when(fermeRepository.searchFerme(critere)).thenReturn(fermes);
        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);

        List<FermeResponseDTO> response = fermeService.searchFerme(critere);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(fermeResponseDTO, response.get(0));
    }
}
