package com.zalarfresh.ZalarFresh.Service;

import com.zalarfresh.ZalarFresh.DTO.request.VenteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.VenteResponseDTO;
import com.zalarfresh.ZalarFresh.Mapper.VenteMapper;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import com.zalarfresh.ZalarFresh.Model.Vente;
import com.zalarfresh.ZalarFresh.Repository.RecolteRepository;
import com.zalarfresh.ZalarFresh.Repository.VenteRepository;
import com.zalarfresh.ZalarFresh.Service.Impl.VenteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class VenteTestService {

    @Mock
    private VenteRepository venteRepository;

    @Mock
    private RecolteRepository recolteRepository;

    @Mock
    VenteMapper venteMapper;

    @InjectMocks
    private VenteServiceImpl venteService;



    @Test
    void CreateVente() {
        VenteRequestDTO venteRequestDTO = new VenteRequestDTO(
                100.0,
                50.0,
                5000.0,
                LocalDate.now(),
                "Client A",
                1L
        );

        Recolte recolte = new Recolte();
        recolte.setId(1L);
        recolte.setQuantity(50);
        recolte.setVente(null);

        Vente vente = new Vente();
        vente.setRecolte(recolte);
        vente.setQuantite(50);
        vente.setPrixUnique(100.0);
        vente.setRevenue(5000.0);

        Vente savedVente = new Vente();
        savedVente.setId(1L);
        savedVente.setRecolte(recolte);
        savedVente.setQuantite(50);
        savedVente.setPrixUnique(100.0);
        savedVente.setRevenue(5000.0);

        ChampResponseDTO champResponseDTO = new ChampResponseDTO
                (1L,
                "Champ A",
                200.0,
                2L
        );

        VenteResponseDTO responseDTO = new VenteResponseDTO(
                1L,
                "Client A",
                100.0,
                LocalDate.of(2021, 1, 1),
                champResponseDTO
        );
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));
        when(venteMapper.toEntity(venteRequestDTO)).thenReturn(vente);
        when(venteRepository.save(vente)).thenReturn(savedVente);
        when(venteMapper.toResponseDTO(savedVente)).thenReturn(responseDTO);

        VenteResponseDTO result = venteService.creationVente(venteRequestDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Client A", result.buyer());
        assertEquals(100.0, result.amount());
        assertNotNull(result.saleDate());
        assertEquals(LocalDate.of(2021, 1, 1), result.saleDate());
        assertNotNull(result.champ());
        assertEquals(1L, result.champ().id());
        assertEquals("Champ A", result.champ().nom());
        assertEquals(200.0, result.champ().surface());
        assertEquals(2L, result.champ().fermeId());


        verify(recolteRepository, times(1)).findById(1L);
        verify(venteRepository, times(1)).save(vente);
    }



    @Test
    void testGetAllVentes() {

        Vente vente1 = new Vente();
        vente1.setId(1L);
        vente1.setPrixUnique(100.0);
        vente1.setQuantite(50);
        vente1.setRevenue(5000.0);

        Vente vente2 = new Vente();
        vente2.setId(2L);
        vente2.setPrixUnique(200.0);
        vente2.setQuantite(30);
        vente2.setRevenue(6000.0);

        VenteResponseDTO responseDTO1 = new VenteResponseDTO(1L, "Client A", 100.0, LocalDate.now(), null);
        VenteResponseDTO responseDTO2 = new VenteResponseDTO(2L, "Client B", 200.0, LocalDate.now(), null);

        when(venteRepository.findAll()).thenReturn(List.of(vente1, vente2));
        when(venteMapper.toResponseDTO(vente1)).thenReturn(responseDTO1);
        when(venteMapper.toResponseDTO(vente2)).thenReturn(responseDTO2);


        List<VenteResponseDTO> result = venteService.GetAllVentes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(2L, result.get(1).id());
        verify(venteRepository, times(1)).findAll();
    }

    @Test
    void testUpdateVente() {
        Long venteId = 1L;
        VenteRequestDTO venteRequestDTO = new VenteRequestDTO(150.0, 50.0, 7500.0, LocalDate.now(), "Updated Client", 1L);

        Vente existingVente = new Vente();
        existingVente.setId(venteId);
        existingVente.setQuantite(50);

        Recolte recolte = new Recolte();
        recolte.setId(1L);

        Vente updatedVente = new Vente();
        updatedVente.setId(venteId);
        updatedVente.setPrixUnique(150.0);
        updatedVente.setRevenue(7500.0);
        updatedVente.setDate(LocalDate.now());
        updatedVente.setClient("Updated Client");
        updatedVente.setRecolte(recolte);

        VenteResponseDTO responseDTO = new VenteResponseDTO(venteId, "Updated Client", 150.0, LocalDate.now(), null);

        when(venteRepository.findById(venteId)).thenReturn(Optional.of(existingVente));
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));
        when(venteRepository.save(any(Vente.class))).thenReturn(updatedVente);
        when(venteMapper.toResponseDTO(updatedVente)).thenReturn(responseDTO);

        VenteResponseDTO result = venteService.updateVente(venteId, venteRequestDTO);

        assertNotNull(result);
        assertEquals(venteId, result.id());
        assertEquals("Updated Client", result.buyer());
        assertEquals(150.0, result.amount());
        verify(venteRepository, times(1)).findById(venteId);
        verify(venteRepository, times(1)).save(any(Vente.class));
        verify(recolteRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteVente() {

        Long venteId = 1L;
        Vente existingVente = new Vente();
        existingVente.setId(venteId);

        when(venteRepository.findById(venteId)).thenReturn(Optional.of(existingVente));

        venteService.deleteVente(venteId);

        verify(venteRepository, times(1)).findById(venteId);
        verify(venteRepository, times(1)).delete(existingVente);
    }

}
