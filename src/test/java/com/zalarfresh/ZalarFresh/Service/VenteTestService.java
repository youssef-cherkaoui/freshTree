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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        Long recolteId = 1L;
        double prixUnique = 100.0;
        double quantite = 10.0;
        double revenue = prixUnique * quantite;
        String client = "Client A";
        LocalDate date = LocalDate.of(2025, 1, 6);
        VenteRequestDTO venteDTO = new VenteRequestDTO(prixUnique, quantite, revenue, date, client, recolteId);

        Recolte recolte = new Recolte();
        recolte.setId(recolteId);
        recolte.setQuantity(10);
        recolte.setVente(null);

        Vente vente = new Vente();
        vente.setRecolte(recolte);
        vente.setQuantite(quantite);
        vente.setPrixUnique(prixUnique);
        vente.setRevenue(revenue);


        Long champId = 1L;
        String champName = "Revenue";
        Double champValue = revenue;
        Long relatedId = 1L;


        ChampResponseDTO champResponseDTO = new ChampResponseDTO(champId, champName, champValue, relatedId);
        VenteResponseDTO responseDTO = new VenteResponseDTO(1L, client, prixUnique, date ,champResponseDTO);

        when(recolteRepository.findById(recolteId)).thenReturn(Optional.of(recolte));
        when(venteMapper.toEntity(venteDTO)).thenReturn(vente);
        when(venteMapper.toResponseDTO(any(Vente.class))).thenReturn(responseDTO);
        when(venteRepository.save(any(Vente.class))).thenReturn(vente);

        VenteResponseDTO result = venteService.creationVente(venteDTO);


        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(venteRepository, times(1)).save(vente);
    }
}
