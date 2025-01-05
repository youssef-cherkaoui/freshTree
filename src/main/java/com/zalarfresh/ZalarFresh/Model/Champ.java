package com.zalarfresh.ZalarFresh.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Champs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du champ ne peut pas être vide")
    private String nom;

    @DecimalMin(value = "0.1", message = "Champ doit être d'au moins 0,1 hectare")
    @Column(nullable = false)
    private Double surface;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ferme_id", nullable = false)
    @JsonIgnore
    private Ferme ferme;

    @OneToMany(mappedBy = "champ")
    private List<Recolte> recolte;


}
