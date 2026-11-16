package br.com.fiap.sampareport.model;

import java.time.LocalDateTime;

public class Comentario {

    private Long id;
    private String texto;
    private LocalDateTime dataCriacao;
    private Long usuarioId;
    private Long reportId;


    public Comentario() {
    }


    public Comentario(String texto, Long usuarioId, Long reportId) {
        this.texto = texto;
        this.usuarioId = usuarioId;
        this.reportId = reportId;
    }

    public Comentario(Long id, String texto, LocalDateTime dataCriacao, Long usuarioId, Long reportId) {
        this.id = id;
        this.texto = texto;
        this.dataCriacao = dataCriacao;
        this.usuarioId = usuarioId;
        this.reportId = reportId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }
}