package br.com.fiap.sampareport.model;

import java.time.LocalDateTime;

public class ReportHistory {

    private Long id;
    private Long reportId;
    private ReportStatus statusAnterior;
    private ReportStatus statusNovo;
    private LocalDateTime dataAlteracao;


    public ReportHistory() {
    }


    public ReportHistory(Long reportId, ReportStatus statusAnterior, ReportStatus statusNovo) {
        this.reportId = reportId;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
    }


    public ReportHistory(Long id, Long reportId, ReportStatus statusAnterior,
                         ReportStatus statusNovo, LocalDateTime dataAlteracao) {
        this.id = id;
        this.reportId = reportId;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataAlteracao = dataAlteracao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }

    public ReportStatus getStatusAnterior() { return statusAnterior; }
    public void setStatusAnterior(ReportStatus statusAnterior) { this.statusAnterior = statusAnterior; }

    public ReportStatus getStatusNovo() { return statusNovo; }
    public void setStatusNovo(ReportStatus statusNovo) { this.statusNovo = statusNovo; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }
}