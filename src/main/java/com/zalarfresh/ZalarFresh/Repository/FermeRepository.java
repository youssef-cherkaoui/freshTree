package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.Model.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long>, FermeRepositoryCustom {
}
