package br.com.rhssolutions.desafioJunior.dto;

import java.time.LocalDate;
import java.util.List;

public record ProjectDTOResponse(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        List<TaskDTOResponse> tasks
) {
}
