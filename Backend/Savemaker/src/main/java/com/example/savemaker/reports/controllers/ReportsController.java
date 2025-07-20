package com.example.savemaker.reports.controllers;

import com.example.savemaker.reports.dtos.ChartReportDataDTO;
import com.example.savemaker.reports.services.ReportsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/reports")
public class ReportsController {

    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping(value = "/chart", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChartReportDataDTO> getChartData(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate
    ) {
        if (startDate == null) {
            startDate = LocalDate.of(1970, 1, 1);
        } else {
            startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        }

        if (endDate == null) {
            endDate = LocalDate.of(2099, 12, 31);
        } else {
            endDate = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        }

        ChartReportDataDTO data = reportsService.getChartReportDataDTO(startDate, endDate);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
