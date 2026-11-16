package br.com.fiap.sampareport.model;

import java.time.LocalDateTime;

public class Avaliacao {

    private Long id;
    private Integer nota;
    private String feedback;
    private LocalDateTime dataAvaliacao;
    private Long usuarioId;
    private Long reportId;


    public Avaliacao() {
    }

    public Avaliacao(Integer nota, String feedback, Long usuarioId, Long reportId) {
        this.nota = nota;
        this.feedback = feedback;
        this.usuarioId = usuarioId;
        this.reportId = reportId;
    }

    public Avaliacao(Long id, Integer nota, String feedback, LocalDateTime dataAvaliacao,
                     Long usuarioId, Long reportId) {
        this.id = id;
        this.nota = nota;
        this.feedback = feedback;
        this.dataAvaliacao = dataAvaliacao;
        this.usuarioId = usuarioId;
        this.reportId = reportId;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }
}