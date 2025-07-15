package com.example.savemaker.balance.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BalanceService {

    String prefix = "balance";

    @GET(prefix)
    Call<Double> getMainBalance();

    @GET(prefix + "/type/{categoryId}")
    Call<Double> getBalanceForCategory(@Path("categoryId") Long categoryId);
}
