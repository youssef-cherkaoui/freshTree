package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.VenteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.VenteResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Exception.ValidationException;
import com.zalarfresh.ZalarFresh.Mapper.VenteMapper;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import com.zalarfresh.ZalarFresh.Model.Vente;
import com.zalarfresh.ZalarFresh.Repository.RecolteRepository;
import com.zalarfresh.ZalarFresh.Repository.VenteRepository;
import com.zalarfresh.ZalarFresh.Service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private VenteMapper venteMapper;



    public VenteResponseDTO creationVente(VenteRequestDTO venteDTO) {

        Recolte recolte = recolteRepository.findById(venteDTO.RecolteId())
                .orElseThrow(() -> new CustomNotFoundException("Recolte non trouve avec id" + venteDTO.RecolteId()));
        if(recolte.getVente() != null ){
            throw new ValidationException("Cette récolte est déjà vendue à un client.");
        }

        Vente vente = venteMapper.toEntity(venteDTO);
        vente.setRecolte(recolte);
        vente.setQuantite(recolte.getQuantity());
        vente.setRevenue(vente.getQuantite() * vente.getPrixUnique());

        Vente savedVente = venteRepository.save(vente);
        return venteMapper.toResponseDTO(savedVente);
    }

    @Override
    public List<VenteResponseDTO> GetAllVentes() {
        return venteRepository.findAll().stream()
                .map(venteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public VenteResponseDTO updateVente(Long id, VenteRequestDTO venteDTO) {

        Vente existVente = venteRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Vente non trouve avec id" + id));

        Recolte recolte = recolteRepository.findById(venteDTO.RecolteId())
                .orElseThrow(()-> new CustomNotFoundException("Recolte non trouve avec id" + venteDTO.RecolteId()));

        existVente.setPrixUnique(venteDTO.PrixUnique());
        existVente.setDate(venteDTO.date());
        existVente.setClient(venteDTO.client());

        existVente.setRevenue(existVente.getQuantite() * existVente.getPrixUnique());
        Vente updatedVente = venteRepository.save(existVente);
        return venteMapper.toResponseDTO(updatedVente);
    }

    public void deleteVente(Long id) {

        Vente vente = venteRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Vente non trouve avec id" + id));
        venteRepository.delete(vente);

    }
}
