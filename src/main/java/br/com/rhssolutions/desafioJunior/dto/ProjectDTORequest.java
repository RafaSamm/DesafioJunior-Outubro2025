package br.com.rhssolutions.desafioJunior.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectDTORequest(
        @NotBlank @Size(min = 3, max = 100) String name,
        String description,
        @NotNull LocalDate startDate,
        LocalDate endDate
) {
}
