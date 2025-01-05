package com.zalarfresh.ZalarFresh.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Localisation ne peut pas être vide")
    private String localisation;

    @DecimalMin(value = "0.1", message = "La surface de l'exploitation doit être supérieure à 0,1 hectare")
    @Column(nullable = false)
    private double surface;

    @PastOrPresent(message ="La date de création doit être dans le passé ou le présent" )
    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Champ> champ;
}
