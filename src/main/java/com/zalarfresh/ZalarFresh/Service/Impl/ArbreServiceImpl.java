package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.ArbreRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ArbreResponseDTO;
import com.zalarfresh.ZalarFresh.DTO.response.ChampResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Exception.ValidationException;
import com.zalarfresh.ZalarFresh.Mapper.ArbreMapper;
import com.zalarfresh.ZalarFresh.Model.Arbre;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Repository.ArbreRepository;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Service.ArbreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ArbreMapper arbreMapper;


    private void validateArbreSpacing(Champ champ){
        int maxArbre = (int) (champ.getSurface() * 100);

        long ActuelNombreCount = arbreRepository.countByChampId(champ.getId());

        if (ActuelNombreCount >= maxArbre){
            throw new ValidationException( String.format("Le champ (ID: %d) dépasse la densité maximale d'arbres : %d arbres pour une surface de %.2f hectares.", champ.getId(), maxArbre, champ.getSurface()) );
        }
    }

    @Transactional
    public ArbreResponseDTO CreateArbre(ArbreRequestDTO arbreDTO) {

        Champ champ = champRepository.findById(arbreDTO.champId())
                .orElseThrow(() -> new CustomNotFoundException("Champ non troue avec id :" + arbreDTO.champId()));

        validateArbreSpacing(champ);

        Arbre arbre = arbreMapper.toEntity(arbreDTO);
        arbre.setChamp(champ);

        log.info("Champ avant sauvegarde: {}", champ);
        log.info("Ferme associée au champ: {}", champ.getFerme());

        Arbre arbreSaved = arbreRepository.save(arbre);
        return arbreMapper.ArbreToArbreResponseDTO(arbreSaved);


    }

    public double calculateProductivity(Long id) {

        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow( ()-> new CustomNotFoundException("Arbre non trouvée avec id" + id));
        return  arbre.calculateProductivity();
    }


    @Transactional
    @Override
    public ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO arbreDTO) {

        Arbre existArbre = arbreRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Arbre non trouvée avec id " +id));

        Champ champ = champRepository.findById(arbreDTO.champId())
                .orElseThrow(()-> new CustomNotFoundException("Arbre non trouvée avec id" +id));

        validateArbreSpacing(champ);
        existArbre.setPlantingDate(arbreDTO.plantingDate());
        existArbre.setChamp(champ);
        Arbre updatedArbre = arbreRepository.save(existArbre);
        return arbreMapper.ArbreToArbreResponseDTO(updatedArbre);
    }

    @Transactional
    @Override
    public ArbreResponseDTO deleteArbre(Long id) {

        Arbre arbre  = arbreRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Arbre non trouvée avec id " +id));
        arbreRepository.delete(arbre);
        return arbreMapper.ArbreToArbreResponseDTO(arbre);
    }

    public List<ArbreResponseDTO> getAllArbres() {
        return arbreRepository.findAll()
                .stream()
                .map(arbreMapper::ArbreToArbreResponseDTO)
                .toList();
    }




}
