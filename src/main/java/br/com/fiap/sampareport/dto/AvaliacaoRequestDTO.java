package br.com.fiap.sampareport.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDTO(
        @NotNull(message = "A nota é obrigatória")
        @Min(value = 0, message = "A nota mínima é 0")
        @Max(value = 5, message = "A nota máxima é 5")
        Integer nota,

        String feedback,

        @NotNull(message = "O ID do usuário avaliador é obrigatório")
        Long usuarioId
) {}