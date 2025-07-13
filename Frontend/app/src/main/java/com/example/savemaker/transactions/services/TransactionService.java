package com.example.savemaker.transactions.services;

import com.example.savemaker.transactions.models.CreateTransaction;
import com.example.savemaker.transactions.models.Transaction;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TransactionService {
    String prefix = "transactions";

    @POST(prefix)
    Call<Transaction> create(@Body CreateTransaction transaction);

    @GET(prefix + "/recent")
    Call<List<Transaction>> getThreeNewest();

    @GET(prefix)
    Call<List<Transaction>> getAll(@Query("categoryId") Long categoryId,
                                   @Query("startDate") LocalDate startDate,
                                   @Query("endDate") LocalDate endDate);
}
