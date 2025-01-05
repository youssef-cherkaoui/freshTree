package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.Model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
