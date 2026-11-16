package br.com.fiap.sampareport.dto;

import jakarta.validation.constraints.NotNull;


public record UpvoteRequestDTO(
        @NotNull(message = "O ID do usuário votante é obrigatório")
        Long usuarioId
) {}