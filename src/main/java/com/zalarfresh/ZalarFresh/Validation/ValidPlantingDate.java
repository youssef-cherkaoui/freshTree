package com.zalarfresh.ZalarFresh.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlantingDateValidator.class)
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlantingDate {

    String message() default "Invalid Planting Date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
