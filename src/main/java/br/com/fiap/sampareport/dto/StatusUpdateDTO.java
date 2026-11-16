package br.com.fiap.sampareport.dto;

import br.com.fiap.sampareport.model.ReportStatus;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateDTO(
        @NotNull(message = "O novo status é obrigatório")
        ReportStatus status
) {}