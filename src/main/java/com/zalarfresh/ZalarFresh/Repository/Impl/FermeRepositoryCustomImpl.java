package com.zalarfresh.ZalarFresh.Repository.Impl;

import com.zalarfresh.ZalarFresh.DTO.request.FermeCriteresRechercheDTO;
import com.zalarfresh.ZalarFresh.Model.Ferme;
import com.zalarfresh.ZalarFresh.Repository.FermeRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FermeRepositoryCustomImpl implements FermeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ferme> searchFerme(FermeCriteresRechercheDTO critereRechercheFerme) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ferme> query = cb.createQuery(Ferme.class);
        Root<Ferme> ferme = query.from(Ferme.class);

        List<Predicate> predicates = new ArrayList<>();

        if(critereRechercheFerme.nom() != null && !critereRechercheFerme.nom().isBlank()) {
            predicates.add(cb.like(cb.lower(ferme.get("nom")),  "%" + critereRechercheFerme.nom().toLowerCase() +"%"));

        }

        if(critereRechercheFerme.localisation() != null && !critereRechercheFerme.localisation().isBlank()) {
            predicates.add(cb.like(cb.lower(ferme.get("localisation")),
                    "%" + critereRechercheFerme.localisation().toLowerCase() + "%"));
        }

        if(critereRechercheFerme.minSurface()!= null){
            predicates.add(cb.greaterThanOrEqualTo(ferme.get("Surface"), critereRechercheFerme.minSurface()));
        }

        if(critereRechercheFerme.maxSurface()!= null){
            predicates.add(cb.lessThanOrEqualTo(ferme.get("Surface"), critereRechercheFerme.maxSurface()));
        }

        if(critereRechercheFerme.creationAvant() != null){
            predicates.add(cb.greaterThanOrEqualTo(ferme.get("creationDate"), critereRechercheFerme.creationAvant()));
        }

        if(critereRechercheFerme.creationApres() != null){
            predicates.add(cb.lessThanOrEqualTo(ferme.get("creationDate") , critereRechercheFerme.creationApres()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}
