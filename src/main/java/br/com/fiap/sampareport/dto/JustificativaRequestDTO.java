package br.com.fiap.sampareport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JustificativaRequestDTO(
        @NotBlank(message = "A URL da foto pós-conserto é obrigatória")
        String fotoDepoisUrl,

        @NotBlank(message = "A nota técnica é obrigatória")
        String notaTecnica,

        @NotNull(message = "O ID do administrador é obrigatório")
        Long adminId
) {}