package br.com.fiap.sampareport.dao;

import br.com.fiap.sampareport.model.Comentario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComentarioDAO {

    private final DataSource dataSource;

    public ComentarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Comentario inserir(Comentario comentario) throws SQLException {
        String sql = "INSERT INTO T_SR_COMMENT (CD_COMENTARIO, DS_TEXTO, DT_CRIACAO, CD_USUARIO, CD_REPORT) " +
                     "VALUES (SQ_SR_COMMENT.NEXTVAL, ?, SYSTIMESTAMP, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"CD_COMENTARIO"})) {

            ps.setString(1, comentario.getTexto());
            ps.setLong(2, comentario.getUsuarioId());
            ps.setLong(3, comentario.getReportId());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    comentario.setId(rs.getLong(1));
                }
            }
            return comentario;
        }
    }

    public List<Comentario> buscarPorReportId(Long reportId) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM T_SR_COMMENT WHERE CD_REPORT = ? ORDER BY DT_CRIACAO ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, reportId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comentarios.add(mapearComentario(rs));
                }
            }
        }
        return comentarios;
    }

    public boolean remover(Long id) throws SQLException {
        String sql = "DELETE FROM T_SR_COMMENT WHERE CD_COMENTARIO = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Comentario mapearComentario(ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp("DT_CRIACAO");
        return new Comentario(
                rs.getLong("CD_COMENTARIO"),
                rs.getString("DS_TEXTO"),
                timestamp != null ? timestamp.toLocalDateTime() : null,
                rs.getLong("CD_USUARIO"),
                rs.getLong("CD_REPORT")
        );
    }
}