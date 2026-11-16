package br.com.fiap.sampareport.controller;

import br.com.fiap.sampareport.bo.ReportBO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final ReportBO reportBO;

    public MetricsController(ReportBO reportBO) {
        this.reportBO = reportBO;
    }

    @GetMapping("/general-rating")
    public ResponseEntity obterAvaliacaoGeral() throws SQLException {

        return ResponseEntity.ok(reportBO.obterMetricasGerais());
    }
}