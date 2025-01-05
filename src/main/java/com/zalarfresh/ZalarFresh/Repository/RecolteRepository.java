package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.Model.Recolte;
import com.zalarfresh.ZalarFresh.Model.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {

    boolean existsBySaisonAndChamp_Id(Saison saison, Long champId);
}
