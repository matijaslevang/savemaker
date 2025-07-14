package com.example.savemaker.transactions.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.savemaker.R;

public class TransactionDetailsDialog extends Dialog implements View.OnClickListener {

    private String date;
    private String notes;
    private String amount;
    private String categoryName;

    public TransactionDetailsDialog(@NonNull Context context, String date, String notes, String amount, String categoryName) {
        super(context);
        this.date = date;
        this.notes = notes;
        this.categoryName = categoryName;
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
        TextView amountTextView = findViewById(R.id.details_amount);
        TextView categoryNameTextView = findViewById(R.id.details_category_name);
        TextView noteTextView = findViewById(R.id.details_note);

        dateTextView.setText(date);
        amountTextView.setText(amount);
        categoryNameTextView.setText(categoryName);
        noteTextView.setText(notes);

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
