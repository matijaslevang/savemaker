package com.example.savemaker.reports;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.savemaker.R;
import com.example.savemaker.databinding.FragmentSimpleReportBinding;
import com.example.savemaker.transactions.adapters.TransactionsAdapter;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.utils.ClientUtils;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleReportFragment extends Fragment {

    private FragmentSimpleReportBinding binding;
    private LocalDate beginningDate = null;
    private LocalDate endingDate = null;
    private TransactionsAdapter adapter;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSimpleReportBinding.inflate(getLayoutInflater());

        setupDatePickers();

        adapter = new TransactionsAdapter(requireContext(), transactions);
        binding.reportsSimpleTransactionRecycler.setAdapter(adapter);
        binding.reportsSimpleTransactionRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        return binding.getRoot();
    }

    private void setupDatePickers() {
        MaterialDatePicker<Long> beginningDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        binding.reportsSimpleBeginningDatePicker.setOnClickListener(v -> {
            beginningDatePicker.show(getChildFragmentManager(), beginningDatePicker.toString());
        });

        beginningDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date beginningDate = new Date(selection);
            String selectedDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                    .format(beginningDate);
            binding.reportsSimpleBeginningDatePicker.setText(selectedDate);
            this.beginningDate = this.convertToLocalDate(beginningDate);
            attemptFetch();
        });

        MaterialDatePicker<Long> endingDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        binding.reportsSimpleEndingDatePicker.setOnClickListener(v -> {
            endingDatePicker.show(getChildFragmentManager(), endingDatePicker.toString());
        });

        endingDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date endingDate = new Date(selection);
            String selectedDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                    .format(endingDate);
            binding.reportsSimpleEndingDatePicker.setText(selectedDate);
            this.endingDate = this.convertToLocalDate(endingDate);
            attemptFetch();
        });
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void attemptFetch() {
        transactions.clear();
        adapter.notifyDataSetChanged();
        if (beginningDate != null && endingDate != null) {
            if (beginningDate.isBefore(endingDate) || beginningDate.isEqual(endingDate)) {
                Call<List<Transaction>> call = ClientUtils.transactionService.getAll(null, beginningDate, endingDate);
                call.enqueue(
                        new Callback<List<Transaction>>() {
                            @Override
                            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    transactions.addAll(response.body());
                                    adapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Transaction>> call, Throwable t) {

                            }
                        }
                );
            } else {
                Toast.makeText(requireContext(), "The end date must be after the beginning date!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}