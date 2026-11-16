package br.com.fiap.sampareport.model;

import java.time.LocalDateTime;

public class Upvote {

    private Long usuarioId;
    private Long reportId;
    private LocalDateTime dataVoto;

    public Upvote() {
    }

    public Upvote(Long usuarioId, Long reportId) {
        this.usuarioId = usuarioId;
        this.reportId = reportId;
    }

    public Upvote(Long usuarioId, Long reportId, LocalDateTime dataVoto) {
        this.usuarioId = usuarioId;
        this.reportId = reportId;
        this.dataVoto = dataVoto;
    }


    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }

    public LocalDateTime getDataVoto() { return dataVoto; }
    public void setDataVoto(LocalDateTime dataVoto) { this.dataVoto = dataVoto; }
}