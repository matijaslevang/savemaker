package com.example.savemaker.transactions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.transactions.dialogs.TransactionDetailsDialog;
import com.example.savemaker.transactions.models.Transaction;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionItemViewHolder> {

    private List<Transaction> transactions;
    private LayoutInflater layoutInflater;
    private Context context;

    public TransactionsAdapter(Context context, List<Transaction> transactions) {
        this.transactions = transactions;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransactionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_holder_transaction, parent, false);
        return new TransactionItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionItemViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.id = transaction.getId();
        holder.notes = transaction.getNotes();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy.", Locale.getDefault());
        holder.date.setText(transaction.getDate().format(formatter));
        holder.category.setText(transaction.getCategory().getName());

        if (transaction.getCategory().getUsedForIncome()) {
            holder.amount.setText(String.format("+%.2f RSD", transaction.getAmount()));
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.button_green));
        } else {
            holder.amount.setText(String.format("-%.2f RSD", transaction.getAmount()));
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.button_red));
        }
    }

    @Override
    public int getItemCount() { return transactions.size(); }

    public static class TransactionItemViewHolder extends RecyclerView.ViewHolder {

        Long id;
        TextView date;
        String notes;
        TextView amount;
        TextView category;

        public TransactionItemViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.transaction_view_holder_date);
            amount = itemView.findViewById(R.id.transaction_view_holder_amount);
            category = itemView.findViewById(R.id.transaction_view_holder_category_name);
            itemView.setOnClickListener(v -> {
                TransactionDetailsDialog dialog = new TransactionDetailsDialog(
                        itemView.getContext(),
                        date.getText().toString(),
                        notes,
                        amount.getText().toString(),
                        category.getText().toString()
                );
                dialog.show();
            });
        }
    }
}
