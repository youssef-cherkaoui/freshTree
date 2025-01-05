package com.zalarfresh.ZalarFresh.DTO.request;

import java.time.LocalDate;

public record ArbreRequestDTO(
        LocalDate plantingDate,
        Long champId

) {
}
