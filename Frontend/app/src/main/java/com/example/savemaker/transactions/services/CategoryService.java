package com.example.savemaker.transactions.services;

import com.example.savemaker.transactions.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    String prefix = "categories";

    @GET(prefix)
    Call<List<Category>> getAll();
}
