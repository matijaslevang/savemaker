package com.example.savemaker.reports.services;

import com.example.savemaker.reports.models.ChartReportData;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportsService {

    String prefix = "reports";

    @GET(prefix + "/chart")
    public Call<ChartReportData> getChartData(@Query("startDate") LocalDate startDate,
                                              @Query("endDate") LocalDate endDate);
}
