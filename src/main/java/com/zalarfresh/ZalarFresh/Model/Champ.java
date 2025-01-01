package com.zalarfresh.ZalarFresh.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Champs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @Column(nullable = false)
    private Double surface;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    @JsonIgnore
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    private List<Recolte> recolte;


}
