package com.zalarfresh.ZalarFresh.Repository;

import com.zalarfresh.ZalarFresh.Model.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {

    long countByChampId(Long ChampId);
    List<Arbre> findByChampId(Long champId);
}
