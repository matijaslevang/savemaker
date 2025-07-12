package com.example.savemaker.utils;

import android.content.Context;

import com.example.savemaker.BuildConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ClientUtils {
    private static final String API_URL = "http://" + BuildConfig.IP_ADDR + ":8080/api/";
    private static Retrofit httpClient;

    public static void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        httpClient = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(makeClient())
                .build();
    }

    private static OkHttpClient makeClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        return client;
    }
}
