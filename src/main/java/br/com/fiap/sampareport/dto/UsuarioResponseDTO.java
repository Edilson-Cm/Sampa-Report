package br.com.fiap.sampareport.dto;

import br.com.fiap.sampareport.model.RoleType;
import br.com.fiap.sampareport.model.Usuario;

import java.time.LocalDateTime;


public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        RoleType perfil,
        LocalDateTime dataCriacao
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getDataCriacao()
        );
    }
}