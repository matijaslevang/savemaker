package com.example.savemaker.overview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savemaker.R;
import com.example.savemaker.balance.adapters.IncomeTypeBalanceAdapter;
import com.example.savemaker.balance.models.IncomeTypeBalance;
import com.example.savemaker.databinding.FragmentOverviewBinding;
import com.example.savemaker.utils.ClientUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewFragment extends Fragment {

    private FragmentOverviewBinding binding;
    private IncomeTypeBalanceAdapter adapter;
    private List<IncomeTypeBalance> balances = new ArrayList<>();

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverviewBinding.inflate(getLayoutInflater());

        adapter = new IncomeTypeBalanceAdapter(requireContext(), balances);
        binding.overviewRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.overviewRecycler.setAdapter(adapter);

        fetchData();

        return binding.getRoot();
    }

    private void fetchData() {
        Call<List<IncomeTypeBalance>> call = ClientUtils.balanceService.getBalanceForAllCategories();
        call.enqueue(
                new Callback<List<IncomeTypeBalance>>() {
                    @Override
                    public void onResponse(Call<List<IncomeTypeBalance>> call, Response<List<IncomeTypeBalance>> response) {
                        if (response.isSuccessful()) {
                            Log.wtf("what", response.body().toString());
                            balances.clear();
                            balances.addAll(response.body());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<IncomeTypeBalance>> call, Throwable t) {

                    }
                }
        );
    }
}