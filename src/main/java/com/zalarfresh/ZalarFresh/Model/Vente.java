package com.zalarfresh.ZalarFresh.Model;


import jakarta.persistence.*;
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

    //@Positive(message = "Unit price must be greater than 0")
    private double PrixUnique;
    private double quantite;
    private double revenue;
    private LocalDate date;
    private String client;
    @OneToOne
    @JoinColumn(name = "harvest_id")
    private Recolte recolte;

}
