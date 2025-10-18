package br.com.rhssolutions.desafioJunior.dto;

import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskDTORequest(
        @NotBlank @Size(min = 5, max = 150) String title,
        String description,
        Status status,
        Priority priority,
        LocalDate dueDate,
        Long projectId
) {
}

