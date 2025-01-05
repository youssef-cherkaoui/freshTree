package com.zalarfresh.ZalarFresh.Service.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.ArbreRecolteDetailsDTO;
import com.zalarfresh.ZalarFresh.DTO.request.RecolteRequestDTO;
import com.zalarfresh.ZalarFresh.DTO.response.RecolteResponseDTO;
import com.zalarfresh.ZalarFresh.Exception.CustomNotFoundException;
import com.zalarfresh.ZalarFresh.Exception.ValidationException;
import com.zalarfresh.ZalarFresh.Mapper.RecolteMapper;
import com.zalarfresh.ZalarFresh.Model.Arbre;
import com.zalarfresh.ZalarFresh.Model.ArbreRecolteDetails;
import com.zalarfresh.ZalarFresh.Model.Champ;
import com.zalarfresh.ZalarFresh.Model.Recolte;
import com.zalarfresh.ZalarFresh.Repository.ArbreRepository;
import com.zalarfresh.ZalarFresh.Repository.ChampRepository;
import com.zalarfresh.ZalarFresh.Repository.RecolteRepository;
import com.zalarfresh.ZalarFresh.Service.RecolteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private RecolteMapper recolteMapper;


    @Transactional
    public RecolteResponseDTO creationRecolte(RecolteRequestDTO recolteDTO) {

        Champ champ = champRepository.findById(recolteDTO.champId())
                .orElseThrow(() -> new CustomNotFoundException("Champ non trouve avec id :" +recolteDTO.champId()));
        if (recolteRepository.existsBySaisonAndChamp_Id(recolteDTO.saison(), recolteDTO.champId())){
            throw new IllegalArgumentException("Une récolte existe déjà pour cette saison et ce champ.");
        }

        List<Arbre> arbres = arbreRepository.findByChampId(champ.getId());

        if(arbres.isEmpty()){
            throw new ValidationException("Aucun arbre trouvé pour le champ spécifié (Id "+ recolteDTO.champId() + ")");
        }

        Recolte recolte = recolteMapper.toEntity(recolteDTO);
        recolte.setChamp(champ);

        double totalQuantity = 0.0;

        if(recolte.getArbreRecolteDetails() != null){
            for (ArbreRecolteDetails details : recolte.getArbreRecolteDetails()) {
                Arbre arbre = arbreRepository.findById(details.getArbre().getId())
                        .orElseThrow(()-> new CustomNotFoundException(
                                "Arbre non trouvée avec id :" +details.getArbre().getId()));

                        double ArbreProductivity = arbre.calculateProductivity();
                        details.setArbre(arbre);
                        details.setRecolte(recolte);
                        details.setQuantite(ArbreProductivity);

                        totalQuantity += ArbreProductivity;
            }
        }
        recolte.setQuantity(totalQuantity);
        Recolte saveRecolte = recolteRepository.save(recolte);
        return recolteMapper.toResponseDto(saveRecolte);
    }

    @Transactional
    @Override
    public RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO recolteDTO) {

        Recolte existRecolte = recolteRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Recolte non trouve avec id :"+id));

        Champ champ = champRepository.findById(recolteDTO.champId())
                .orElseThrow(()-> new CustomNotFoundException("Champ non trouve avec id :" +recolteDTO.champId()));

        existRecolte.setSaison(recolteDTO.saison());
        existRecolte.setDate(recolteDTO.date());
        existRecolte.setChamp(champ);

        double totalQuantity = 0.0;
        existRecolte.getArbreRecolteDetails().clear();
        for (ArbreRecolteDetailsDTO detailsDTO : recolteDTO.arbreRecolteDetails()){
            Long arbreId = detailsDTO.ArbreId();
            Arbre arbre = arbreRepository.findById(arbreId)
                    .orElseThrow(()-> new ValidationException("Arbre non trouve avec id :" +arbreId));

            if(!arbre.getChamp().getId().equals(champ.getId())){
                throw new ValidationException(
                    "L'arbre avec l'ID " + arbreId +"  n'appartient pas au champ spécifié (Id:"
                        +recolteDTO.champId() + ")"
                );
            }

            ArbreRecolteDetails details = new ArbreRecolteDetails();
            details.setArbre(arbre);
            details.setRecolte(existRecolte);

            double arbreProductivity = arbre.calculateProductivity();
            details.setQuantite(arbreProductivity);

            totalQuantity += arbreProductivity;
            existRecolte.getArbreRecolteDetails().add(details);
        }

        existRecolte.setQuantity(totalQuantity);
        Recolte updateRecolte = recolteRepository.save(existRecolte);

        return recolteMapper.toResponseDto(updateRecolte);
    }


    @Transactional
    @Override
    public RecolteResponseDTO deleteRecolte(Long id) {

        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(()-> new CustomNotFoundException("Recolte non trouve avec id :" +id));
        recolteRepository.delete(recolte);
        return recolteMapper.toResponseDto(recolte);
    }

    public List<RecolteResponseDTO> getRecoltes() {

        return recolteRepository.findAll()
                .stream()
                .map(recolteMapper :: toResponseDto)
                .toList();
    }
}
