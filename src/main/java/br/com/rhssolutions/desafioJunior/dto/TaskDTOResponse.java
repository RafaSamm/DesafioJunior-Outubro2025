package br.com.rhssolutions.desafioJunior.dto;

import br.com.rhssolutions.desafioJunior.domain.enums.Priority;
import br.com.rhssolutions.desafioJunior.domain.enums.Status;

import java.time.LocalDate;

public record TaskDTOResponse(
        Long id,
        String title,
        String description,
        Status status,
        Priority priority,
        LocalDate dueDate
) {
}
