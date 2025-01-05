package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.Model.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {

    long countByFermeId(Long fermeId);
    List<Champ> findAllByFermeId(Long fermeId);
    boolean existsByFermeId(Long fermeId);
}
