package com.zalarfresh.ZalarFresh.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "date plant est obligatoire")
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    public int getAge() {
        if (plantingDate == null) {
            return 0;
        }
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    public double calculateProductivity() {
        int age = getAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;
        }

    }

    public boolean isProductive() {
        return getAge() <= 20;
    }

}
