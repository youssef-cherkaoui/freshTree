package com.zalarfresh.ZalarFresh.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Le prix unitaire doit être supérieur à 00")
    private double PrixUnique;

    @Positive(message = "La quantité doit être supérieur à 00")
    private double quantite;

    @Column(nullable = false )
    private double revenue;

    @NotNull(message = "La date est obligatoire")
    @FutureOrPresent(message = "La date ne peut pas être dans le passé")
    private LocalDate date;

    @NotBlank(message = "le nom du client est obligatoire")
    private String client;

    @OneToOne
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;

}
