package com.example.savemaker.settings.services;

import com.example.savemaker.settings.models.PreferredPriorityElement;
import com.example.savemaker.settings.models.PriorityListElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface SettingsService {
    String prefix = "settings";
    @GET(prefix + "/prioritylist")
    Call<List<PriorityListElement>> getPriorityList();
    @PUT(prefix + "/prioritylist")
    Call<Boolean> updatePriorityList(@Body List<PriorityListElement> newList);
    @GET(prefix + "/preferredpriority")
    Call<List<PreferredPriorityElement>> getPreferredPriorityList();
    @PUT(prefix + "/preferredpriority")
    Call<Boolean> updatePreferredPriorityList(@Body List<PreferredPriorityElement> newList);
}
