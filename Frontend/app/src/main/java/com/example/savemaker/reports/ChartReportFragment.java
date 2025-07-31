package com.example.savemaker.reports;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.savemaker.R;
import com.example.savemaker.common.dialogs.NoticeDialog;
import com.example.savemaker.databinding.FragmentChartReportBinding;
import com.example.savemaker.reports.models.ChartReportData;
import com.example.savemaker.transactions.adapters.TransactionsAdapter;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.utils.ClientUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

public class ChartReportFragment extends Fragment {

    private FragmentChartReportBinding binding;
    private LocalDate beginningDate = null;
    private LocalDate endingDate = null;
    private PieChart incomePieChart;
    private PieChart expensePieChart;
    private List<Transaction> transactions = new ArrayList<>();

    public ChartReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChartReportBinding.inflate(inflater);

        setupDatePickers();
        incomePieChart = binding.reportsChartIncomePieChart;
        expensePieChart = binding.reportsChartExpensePieChart;
        incomePieChart.setNoDataText(getString(R.string.no_chart_data_text));
        incomePieChart.setNoDataTextColor(Color.BLACK);
        incomePieChart.setEntryLabelColor(Color.BLACK);
        expensePieChart.setNoDataText(getString(R.string.no_chart_data_text));
        expensePieChart.setNoDataTextColor(Color.BLACK);
        expensePieChart.setEntryLabelColor(Color.BLACK);

        return binding.getRoot();
    }

    private void setupDatePickers() {
        MaterialDatePicker<Long> beginningDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        binding.reportsChartBeginningDatePicker.setOnClickListener(v -> {
            beginningDatePicker.show(getChildFragmentManager(), beginningDatePicker.toString());
        });

        beginningDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date beginningDate = new Date(selection);
            String selectedDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                    .format(beginningDate);
            binding.reportsChartBeginningDatePicker.setText(selectedDate);
            this.beginningDate = this.convertToLocalDate(beginningDate);
            attemptFetch();
        });

        MaterialDatePicker<Long> endingDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        binding.reportsChartEndingDatePicker.setOnClickListener(v -> {
            endingDatePicker.show(getChildFragmentManager(), endingDatePicker.toString());
        });

        endingDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date endingDate = new Date(selection);
            String selectedDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                    .format(endingDate);
            binding.reportsChartEndingDatePicker.setText(selectedDate);
            this.endingDate = this.convertToLocalDate(endingDate);
            attemptFetch();
        });
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void attemptFetch() {
        clearCharts();
        if (beginningDate != null && endingDate != null) {
            if (beginningDate.isBefore(endingDate) || beginningDate.isEqual(endingDate)) {
                Call<ChartReportData> call = ClientUtils.reportsService.getChartData(beginningDate, endingDate);
                call.enqueue(
                        new Callback<ChartReportData>() {
                            @Override
                            public void onResponse(Call<ChartReportData> call, Response<ChartReportData> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    drawCharts(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<ChartReportData> call, Throwable t) {

                            }
                        }
                );
            } else {
                new NoticeDialog(requireContext(), "Invalid input", "The end date must be after the beginning date!").show();
            }
        }
    }

    private void clearCharts() {
        incomePieChart.clear();
        incomePieChart.invalidate();
        expensePieChart.clear();
        expensePieChart.invalidate();
    }

    private void drawCharts(ChartReportData data) {
        ArrayList<PieEntry> incomeChartEntries = new ArrayList<>();
        ArrayList<PieEntry> expenseChartEntries = new ArrayList<>();

        for (int i = 0; i < data.getAmounts().size(); i++) {
            if (data.getCategories().get(i).getUsedForIncome()) {
                incomeChartEntries.add(new PieEntry(data.getAmounts().get(i).floatValue(), data.getCategories().get(i).getName()));
            } else {
                expenseChartEntries.add(new PieEntry(data.getAmounts().get(i).floatValue(), data.getCategories().get(i).getName()));
            }
        }

        PieDataSet incomeDataSet = new PieDataSet(incomeChartEntries, getString(R.string.income_categories));
        List<Integer> incomeColors = new ArrayList<>();
        for (int i = 0; i < incomeChartEntries.size(); i++) {
            float hue = (i * 360f / incomeChartEntries.size());
            incomeColors.add(Color.HSVToColor(new float[] { hue, 1f, 1f }));
        }
        incomeDataSet.setColors(incomeColors);

        PieDataSet expenseDataSet = new PieDataSet(expenseChartEntries, getString(R.string.expense_categories));
        List<Integer> expenseColors = new ArrayList<>();
        for (int i = 0; i < expenseChartEntries.size(); i++) {
            float hue = ((i * 360f / expenseChartEntries.size()) + 180f) % 360f;
            expenseColors.add(Color.HSVToColor(new float[] { hue, 1f, 1f }));
        }
        expenseDataSet.setColors(expenseColors);


        PieData incomePieData = new PieData(incomeDataSet);
        incomePieData.setValueTextSize(16f);
        incomePieChart.setData(incomePieData);
        incomePieChart.setCenterText(getString(R.string.income));
        incomePieChart.setDrawHoleEnabled(true);
        incomePieChart.getDescription().setEnabled(false);
        incomePieChart.animateY(1000);
        incomePieChart.invalidate();

        PieData expensePieData = new PieData(expenseDataSet);
        expensePieData.setValueTextSize(16f);
        expensePieChart.setData(expensePieData);
        expensePieChart.setCenterText(getString(R.string.expense));
        expensePieChart.setDrawHoleEnabled(true);
        expensePieChart.getDescription().setEnabled(false);
        expensePieChart.animateY(1000);
        expensePieChart.invalidate();
    }
}