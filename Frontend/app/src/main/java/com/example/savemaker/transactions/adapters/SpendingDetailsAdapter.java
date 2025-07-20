package com.example.savemaker.transactions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.balance.models.SpendingDetails;

import java.util.List;

public class SpendingDetailsAdapter extends RecyclerView.Adapter<SpendingDetailsAdapter.SpendingDetailsViewHolder> {

    private List<SpendingDetails> spendingDetails;
    private LayoutInflater layoutInflater;

    public SpendingDetailsAdapter(Context context, List<SpendingDetails> spendingDetails) {
        layoutInflater = LayoutInflater.from(context);
        this.spendingDetails = spendingDetails;
    }

    @NonNull
    @Override
    public SpendingDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_holder_spending_details, parent, false);
        return new SpendingDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendingDetailsViewHolder holder, int position) {
        SpendingDetails details = spendingDetails.get(position);
        String categoryName = details.getCategoryName() + ":";
        String amount = details.getAmount() + " RSD";
        holder.categoryName.setText(categoryName);
        holder.amount.setText(amount);
    }

    @Override
    public int getItemCount() {
        return spendingDetails.size();
    }

    public static class SpendingDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        TextView amount;
        public SpendingDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.sd_view_holder_category_name);
            amount = itemView.findViewById(R.id.sd_view_holder_amount);
        }
    }
}
