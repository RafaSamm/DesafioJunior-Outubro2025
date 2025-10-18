package br.com.rhssolutions.desafioJunior.dto;

import br.com.rhssolutions.desafioJunior.domain.enums.Status;
import jakarta.validation.constraints.NotNull;

public record TaskStatusUpdateDTO(
        @NotNull Status status
) {

}
