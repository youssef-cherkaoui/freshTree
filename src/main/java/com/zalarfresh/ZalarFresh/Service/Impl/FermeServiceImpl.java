package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.DTO.request.FermeRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.FermeResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Mapper.FermeMapper;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Repository.FermeRepository;
import com.zalarfresh.ZalarFresh.Service.FermeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {


    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private FermeMapper fermeMapper;

    @Transactional
    public FermeResponseDTO creationFerme(FermeRequestDTO fermeDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);

        return fermeMapper.toResponseDTO(savedFerme);
    }


    public Page<FermeResponseDTO> getAllFermes(Pageable pageable) {
        Page<Ferme> ferme = fermeRepository.findAll(pageable);
        return ferme
                .map(fermeMapper::toResponseDTO);

    }

    public Page<Ferme> getAllFermes2(Pageable pageable) {
        Page<Ferme> ferme = fermeRepository.findAll(pageable);
        return ferme;
    }


    public FermeResponseDTO getFermeById(Long id) {

        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("ferme non trouvée avec id" +id));

        return fermeMapper.toResponseDTO(ferme);
    }



    @Transactional
    public FermeResponseDTO updateFerme(Long id, FermeRequestDTO fermeDTO) {

        var ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("ferme non trouvée avec id" +id));


       ferme.setNom(fermeDTO.nom());
       ferme.setLocalisation(fermeDTO.localisation());
       ferme.setSurface(fermeDTO.surface());
       ferme.setCreationDate(fermeDTO.creationDate());


        Ferme updatedFerme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(updatedFerme);
    }

    @Transactional
    public void deleteFerme(Long id) {
        if(!fermeRepository.existsById(id)) {
            throw new CustomNotFoundException("ferme non trouvée avec id" +id);
        }
        fermeRepository.deleteById(id);

    }


    public List<FermeResponseDTO> searchFerme(FermeCriteresRechercheDTO critere) {
        return fermeRepository.searchFerme(critere)
                .stream()
                .map(fermeMapper :: toResponseDTO)
                .toList();
    }
}
