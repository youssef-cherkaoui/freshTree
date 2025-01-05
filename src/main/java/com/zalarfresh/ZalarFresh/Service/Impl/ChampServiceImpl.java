package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.ChampRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Exception.ValidationException;
import com.zalarfresh.ZalarFresh.Mapper.ChampMapper;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Repository.FermeRepository;
import com.zalarfresh.ZalarFresh.Service.ChampService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;

    private void ValidationChampConstraints(Ferme ferme, ChampRequestDTO champDTO) {
        if (champDTO.surface() < 0.1) {
            throw new ValidationException("La superficie du terrain doit être d'au moins 0,1 hectare.");
        }

        double MaxChampSurface = ferme.getSurface() * 0.5;
        if (champDTO.surface() > MaxChampSurface) {
            throw new ValidationException("La surface du terrain ne peut excéder 50% de la surface totale de l'exploitation.");
        }

        long champCount = champRepository.countByFermeId(ferme.getId());
        if (champCount > 10) {
            throw new ValidationException("Une ferme ne peut pas contenir plus de 10 champs.");
        }
    }

    @Transactional
    public ChampResponseDTO creationChamp(ChampRequestDTO champDTO) {
        System.out.println("fermeId : " + champDTO.fermeId());

        Ferme ferme = fermeRepository.findById(champDTO.fermeId())
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec id:" + champDTO.fermeId()));

        //ValidationChampConstraints(ferme, champDTO);
        Champ champ = champMapper.toEntity(champDTO);
        champ.setFerme(ferme);
        Champ savedChamp = champRepository.save(champ);
        return champMapper.toResponseDTO(savedChamp);
    }

    public ChampResponseDTO getChampById(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'Id " + id));
        return champMapper.toResponseDTO(champ);
    }

    @Transactional
    public ChampResponseDTO updateChamp(Long id, ChampRequestDTO champDTO) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'Id:" + id));
        Ferme ferme = fermeRepository.findById(champDTO.fermeId())
                .orElseThrow(() -> new CustomNotFoundException("Ferme non trouvée avec id:" + champDTO.fermeId()));
        ValidationChampConstraints(ferme, champDTO);
        champ.setNom(champDTO.nom());
        champ.setFerme(ferme);
        champ.setSurface(champDTO.surface());

        Champ updatedChamp = champRepository.save(champ);
        return champMapper.toResponseDTO(updatedChamp);
    }

    @Transactional
    @Override
    public ChampResponseDTO deleteChamp(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouvé avec l'Id:" + id));
        champRepository.delete(champ);
        return champMapper.toResponseDTO(champ);
    }

    @Override
    public List<ChampResponseDTO> getAllChamps() {
        List<Champ> champs = champRepository.findAll();
        return champMapper.toResponseDTOList(champs);
    }
}
