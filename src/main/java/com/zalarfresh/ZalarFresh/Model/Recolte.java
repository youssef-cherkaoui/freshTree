package com.zalarfresh.ZalarFresh.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Saison saison;

    private LocalDate date;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

//    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TreeHarvestDetail> treeHarvestDetails;
//
    @OneToOne(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vente vente;
}
