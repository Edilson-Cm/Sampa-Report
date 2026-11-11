package br.com.fiap.sampareport.dao;

import br.com.fiap.sampareport.model.Usuario;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UsuarioDAO {

    private final DataSource dataSource;


    public UsuarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_SR_USUARIO (CD_USUARIO, NM_USUARIO, DS_EMAIL, DS_SENHA, DS_PERFIL) " +
                     "VALUES (SQ_SR_USUARIO.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getPerfil().name());

            ps.executeUpdate();
        }
    }
}