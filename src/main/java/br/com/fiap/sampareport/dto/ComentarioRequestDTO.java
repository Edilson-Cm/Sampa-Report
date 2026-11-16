package br.com.fiap.sampareport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ComentarioRequestDTO(
        @NotBlank(message = "O texto do comentário não pode ser vazio")
        String texto,

        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId
) {}