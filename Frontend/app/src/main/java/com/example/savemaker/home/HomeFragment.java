package com.example.savemaker.home;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savemaker.R;
import com.example.savemaker.animations.AnimatedCircleView;
import com.example.savemaker.databinding.FragmentHomeBinding;
import com.example.savemaker.transactions.dialogs.TransactionCreationDialog;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.CreateTransaction;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.utils.ClientUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<Category> allCategories;

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnimatedCircleView circleView = binding.animatedCircle;
        circleView.post(circleView::startAnimation);

        TextView balanceView = binding.balance;
        animateBalance(balanceView, 99999.99f, "RSD");

        View leftButton = binding.leftButton;
        View rightButton = binding.rightButton;

        Call<List<Category>> call = ClientUtils.categoryService.getAll();
        call.enqueue(
                new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.isSuccessful()) {
                            ClientUtils.allCategories = response.body();
                        } else {
                            Log.e("HomeFragment", "Unsuccessful response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error loading categories!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        leftButton.setOnClickListener(v -> {
            TransactionCreationDialog dialog = new TransactionCreationDialog(
                    getContext(),
                    getChildFragmentManager(),
                    ClientUtils.allCategories.stream().filter(category -> !category.getUsedForIncome()).collect(Collectors.toList()),
                    false,
                    (amount, notes, date, categoryId) -> {
                        ClientUtils.transactionService.create(new CreateTransaction(date, amount, notes, categoryId)).enqueue(
                                new Callback<Transaction>() {
                                    @Override
                                    public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                        // TODO: add into most recent, and subtract from total balance
                                        Toast.makeText(getContext(), "Expense added successfully!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Transaction> call, Throwable t) {
                                        Toast.makeText(getContext(), "Error adding expense!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
            );
            dialog.show();
        });

        rightButton.setOnClickListener(v -> {
            TransactionCreationDialog dialog = new TransactionCreationDialog(
                    getContext(),
                    getChildFragmentManager(),
                    ClientUtils.allCategories.stream().filter(category -> category.getUsedForIncome()).collect(Collectors.toList()),
                    true,
                    (amount, notes, date, categoryId) -> {
                        ClientUtils.transactionService.create(new CreateTransaction(date, amount, notes, categoryId)).enqueue(
                                new Callback<Transaction>() {
                                    @Override
                                    public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                        // TODO: add into most recent, and add to total balance
                                        Toast.makeText(getContext(), "Income added successfully!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Transaction> call, Throwable t) {
                                        Toast.makeText(getContext(), "Error adding income!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
            );
            dialog.show();
        });


    }

    private void animateBalance(TextView balanceTextView, float finalAmount, String currencySymbol) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, finalAmount);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            String formatted = String.format(Locale.getDefault(), "%.2f %s", value, currencySymbol);
            balanceTextView.setText(formatted);
        });

        animator.start();
    }
}