package com.zalarfresh.ZalarFresh.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Ferme")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String localization;

    @Column(nullable = false)
    private double surface;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Champ> champs;
}
