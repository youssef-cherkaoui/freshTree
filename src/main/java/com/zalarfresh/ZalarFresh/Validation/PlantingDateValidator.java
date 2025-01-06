package com.zalarfresh.ZalarFresh.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PlantingDateValidator implements ConstraintValidator<ValidPlantingDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate plantingDate, ConstraintValidatorContext Context) {
        System.out.println("Planting date is: " + plantingDate);
        if (plantingDate == null)
            return false;

        int month = plantingDate.getMonthValue();

        return month >= 3 && month <=5;
        // Planting date must be between March and May

    }
}
