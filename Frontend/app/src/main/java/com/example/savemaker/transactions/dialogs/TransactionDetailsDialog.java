package com.example.savemaker.transactions.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.balance.models.SpendingDetails;
import com.example.savemaker.transactions.adapters.SpendingDetailsAdapter;

import java.util.List;

public class TransactionDetailsDialog extends Dialog implements View.OnClickListener {

    private String date;
    private String notes;
    private String amount;
    private String categoryName;
    private List<SpendingDetails> spendingDetails;
    private Context context;
    private SpendingDetailsAdapter adapter;

    public TransactionDetailsDialog(@NonNull Context context, String date, String notes, String amount, String categoryName, List<SpendingDetails> spendingDetails) {
        super(context);
        this.context = context;
        this.date = date;
        this.notes = notes;
        this.categoryName = categoryName;
        this.spendingDetails = spendingDetails;
        this.amount = amount;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getWindow() != null) {
            getWindow().setLayout(
                    (int)(getContext().getResources().getDisplayMetrics().widthPixels * 0.9),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_transaction_details);

        TextView dateTextView = findViewById(R.id.details_date);
        RecyclerView amountRecycler = findViewById(R.id.details_amount_recycler);
        TextView categoryNameTextView = findViewById(R.id.details_category_name);
        TextView noteTextView = findViewById(R.id.details_note);
        TextView noteLabel = findViewById(R.id.details_note_label);

        dateTextView.setText(date);

        adapter = new SpendingDetailsAdapter(context, spendingDetails);
        amountRecycler.setAdapter(adapter);
        amountRecycler.setLayoutManager(new LinearLayoutManager(context));

        categoryNameTextView.setText(categoryName);
        noteTextView.setText(notes);

        if (notes.isEmpty()) {
            noteTextView.setVisibility(View.GONE);
            noteLabel.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
