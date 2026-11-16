package br.com.fiap.sampareport.bo;

import br.com.fiap.sampareport.dao.UsuarioDAO;
import br.com.fiap.sampareport.dto.LoginRequestDTO;
import br.com.fiap.sampareport.dto.UsuarioRequestDTO;
import br.com.fiap.sampareport.dto.UsuarioResponseDTO;
import br.com.fiap.sampareport.exception.RecursoNaoEncontradoException;
import br.com.fiap.sampareport.exception.RegraNegocioException;
import br.com.fiap.sampareport.model.RoleType;
import br.com.fiap.sampareport.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// @Service avisa ao Spring que esta classe guarda regras de negócio e pode ser injetada nos Controllers
@Service
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO;

    // Injeção de dependência do DAO via construtor
    public UsuarioBO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // 1. CADASTRAR NOVO USUÁRIO
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) throws SQLException {
        // Regra de negócio: não permitir dois cadastros com o mesmo e-mail (dispara HTTP 409)
        if (usuarioDAO.buscarPorEmail(dto.email()).isPresent()) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com o e-mail: " + dto.email());
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha());
        // Se o front-end não mandar o perfil, assume automaticamente que é cidadão comum (ROLE_USER)
        novoUsuario.setPerfil(dto.perfil() != null ? dto.perfil() : RoleType.ROLE_USER);

        usuarioDAO.cadastrar(novoUsuario);

        // Busca o registro recém-salvo para devolver o ID e a data gerados pelo Oracle (sem a senha)
        Usuario usuarioSalvo = usuarioDAO.buscarPorEmail(dto.email())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Erro ao recuperar usuário recém-cadastrado."));

        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }

    // 2. AUTENTICAR (LOGIN)
    public UsuarioResponseDTO autenticar(LoginRequestDTO dto) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmail(dto.email())
                .orElseThrow(() -> new RegraNegocioException("E-mail ou senha inválidos."));

        // Confere se a senha digitada bate com a senha gravada no banco
        if (!usuario.getSenha().equals(dto.senha())) {
            throw new RegraNegocioException("E-mail ou senha inválidos.");
        }

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    // 3. BUSCAR POR ID
    public UsuarioResponseDTO buscarPorId(Long id) throws SQLException {
        // Se não achar o ID no Oracle, lança exceção que vira HTTP 404 no GlobalExceptionHandler
        Usuario usuario = usuarioDAO.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    // 4. LISTAR TODOS
    public List<UsuarioResponseDTO> listarTodos() throws SQLException {
        // Converte cada entidade Usuario da lista em UsuarioResponseDTO (escondendo as senhas)
        return usuarioDAO.listarTodos().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    // 5. ATUALIZAR DADOS DO USUÁRIO
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) throws SQLException {
        // Garante que o usuário existe antes de tentar atualizar (senão devolve 404)
        Usuario usuarioExistente = usuarioDAO.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));

        // Verifica se o novo e-mail já pertence a OUTRA pessoa no banco
        Optional<Usuario> usuarioComMesmoEmail = usuarioDAO.buscarPorEmail(dto.email());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new RegraNegocioException("Este e-mail já está em uso por outro usuário.");
        }

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setEmail(dto.email());
        usuarioExistente.setSenha(dto.senha());
        if (dto.perfil() != null) {
            usuarioExistente.setPerfil(dto.perfil());
        }

        usuarioDAO.atualizar(usuarioExistente);
        return UsuarioResponseDTO.fromEntity(usuarioExistente);
    }

    // 6. REMOVER USUÁRIO
    public void remover(Long id) throws SQLException {
        boolean removido = usuarioDAO.remover(id);
        // Se o DELETE afetou 0 linhas, significa que o ID não existia (devolve 404)
        if (!removido) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado para exclusão com ID: " + id);
        }
    }
}