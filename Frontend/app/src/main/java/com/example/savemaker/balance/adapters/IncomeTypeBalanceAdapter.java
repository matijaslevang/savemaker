package com.example.savemaker.balance.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.animations.AnimatedCircleView;
import com.example.savemaker.balance.models.IncomeTypeBalance;


import java.util.List;
import java.util.Locale;

public class IncomeTypeBalanceAdapter extends RecyclerView.Adapter<IncomeTypeBalanceAdapter.IncomeTypeBalanceViewHolder> {

    private List<IncomeTypeBalance> balances;
    private LayoutInflater layoutInflater;

    public IncomeTypeBalanceAdapter(Context context, List<IncomeTypeBalance> balances) {
        this.balances = balances;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public IncomeTypeBalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_holder_income_type_balance, parent, false);
        return new IncomeTypeBalanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeTypeBalanceViewHolder holder, int position) {
        IncomeTypeBalance incomeTypeBalance = balances.get(position);
        holder.title.setText(incomeTypeBalance.getTypeName());
        drawCircle(holder.circle);
        drawBalance(incomeTypeBalance.getIncomeTypeBalance().floatValue() ,holder.balance);
    }

    @Override
    public int getItemCount() {
        return balances.size();
    }

    private void drawCircle(AnimatedCircleView circleView) {
        circleView.post(circleView::startAnimation);
    }

    private void drawBalance(float currentAmount, TextView balanceView) {
        animateBalance(balanceView, 0f, currentAmount, "RSD");
    }

    private void animateBalance(TextView balanceTextView, float beginningAmount, float finalAmount, String currencySymbol) {
        ValueAnimator animator = ValueAnimator.ofFloat(beginningAmount, finalAmount);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            String formatted = String.format(Locale.getDefault(), "%.2f %s", value, currencySymbol);
            balanceTextView.setText(formatted);
        });

        animator.start();
    }

    public static class IncomeTypeBalanceViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView balance;
        AnimatedCircleView circle;
        public IncomeTypeBalanceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itb_view_holder_type_name);
            balance = itemView.findViewById(R.id.itb_view_holder_balance);
            circle = itemView.findViewById(R.id.itb_view_holder_circle);
        }

    }
}
