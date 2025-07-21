package com.example.savemaker.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savemaker.R;
import com.example.savemaker.databinding.FragmentSettingsBinding;
import com.example.savemaker.settings.dialogs.PreferredPriorityDialog;
import com.example.savemaker.settings.dialogs.PriorityListDialog;
import com.example.savemaker.settings.models.PreferredPriorityElement;
import com.example.savemaker.settings.models.PriorityListElement;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.utils.ClientUtils;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        binding.settingsEditPriorityListButton.setOnClickListener(v -> {
            Call<List<PriorityListElement>> call = ClientUtils.settingsService.getPriorityList();
            call.enqueue(
                    new Callback<List<PriorityListElement>>() {
                        @Override
                        public void onResponse(Call<List<PriorityListElement>> call, Response<List<PriorityListElement>> response) {
                            if (response.isSuccessful()) {
                                PriorityListDialog dialog = new PriorityListDialog(requireContext(), response.body());
                                dialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<PriorityListElement>> call, Throwable t) {

                        }
                    }
            );
        });

        binding.settingsEditPreferredPriorityButton.setOnClickListener(v -> {
            Call<List<PreferredPriorityElement>> call = ClientUtils.settingsService.getPreferredPriorityList();
            call.enqueue(
                    new Callback<List<PreferredPriorityElement>>() {
                        @Override
                        public void onResponse(Call<List<PreferredPriorityElement>> call, Response<List<PreferredPriorityElement>> response) {
                            if (response.isSuccessful()) {
                                PreferredPriorityDialog dialog = new PreferredPriorityDialog(requireContext(), response.body(), ClientUtils.allCategories.stream().filter(v -> v.getUsedForIncome()).collect(Collectors.toList()));
                                dialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<PreferredPriorityElement>> call, Throwable t) {

                        }
                    }
            );
        });

        return binding.getRoot();
    }
}