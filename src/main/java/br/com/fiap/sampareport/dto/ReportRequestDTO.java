package br.com.fiap.sampareport.dto;

import br.com.fiap.sampareport.model.ReportType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "A categoria é obrigatória")
        ReportType categoria,

        @NotNull(message = "A latitude é obrigatória")
        Double latitude,

        @NotNull(message = "A longitude é obrigatória")
        Double longitude,

        @NotBlank(message = "A URL da foto inicial é obrigatória")
        String fotoAntesUrl,

        @NotNull(message = "O ID do usuário autor é obrigatório")
        Long usuarioId
) {}