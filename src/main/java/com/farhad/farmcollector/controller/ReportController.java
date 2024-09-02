package com.farhad.farmcollector.controller;

import com.farhad.farmcollector.service.ReportService;
import com.farhad.farmcollector.service.model.ReportDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService ReportService) {
        this.reportService = ReportService;
    }

    @GetMapping("/season")
    public ResponseEntity<List<ReportDTO>> getReportBySeason(@RequestParam String season) {
        List<ReportDTO> report = reportService.getReportBySeason(season);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/crop")
    public ResponseEntity<List<ReportDTO>> getReportByCropType(@RequestParam String cropType) {
        List<ReportDTO> report = reportService.getReportByCropType(cropType);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/all-seasons")
    public ResponseEntity<List<ReportDTO>> getReportForAllSeasons() {
        List<ReportDTO> report = reportService.getReportForAllSeasons();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
