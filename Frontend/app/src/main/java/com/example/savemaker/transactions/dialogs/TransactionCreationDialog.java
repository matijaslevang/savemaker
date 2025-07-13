package com.example.savemaker.transactions.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import com.example.savemaker.R;
import com.example.savemaker.databinding.DialogTransactionCreationBinding;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.utils.ClientUtils;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

public class TransactionCreationDialog extends Dialog implements View.OnClickListener {

    public interface TransactionCreationDataListener {
        void onDataReceived(Double amount, String notes, LocalDate date, Long categoryId);
    }

    private List<Category> categories;
    private TransactionCreationDataListener callback;
    private Boolean isUsedForIncome;
    private FragmentManager fragmentManager;
    private Context context;


    public TransactionCreationDialog(@NonNull Context context, FragmentManager fragmentManager, List<Category> categories, Boolean isUsedForIncome, TransactionCreationDataListener callback) {
        super(context);
        this.context = context;
        this.isUsedForIncome = isUsedForIncome;
        this.fragmentManager = fragmentManager;
        this.categories = categories;
        this.callback = callback;
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
        setContentView(R.layout.dialog_transaction_creation);

        TextView dialogTitle = findViewById(R.id.dialog_title);
        if (this.isUsedForIncome) {
            dialogTitle.setText(R.string.dialog_title_income);
        } else {
            dialogTitle.setText(R.string.dialog_title_expense);
        }

        Spinner categoryPicker = findViewById(R.id.category_picker);
        TextView datePicker = findViewById(R.id.date_picker);
        EditText amountInput = findViewById(R.id.amount_input);
        EditText notesInput = findViewById(R.id.notes_input);


        ArrayAdapter<Category> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                categories
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryPicker.setAdapter(adapter);

        Button cancelButton = findViewById(R.id.button_cancel);
        Button confirmButton = findViewById(R.id.button_confirm);

        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        String todayDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                .format(new Date(MaterialDatePicker.todayInUtcMilliseconds()));
        datePicker.setText(todayDate);

        datePicker.setOnClickListener(v -> {
            picker.show(fragmentManager, picker.toString());
        });

        picker.addOnPositiveButtonClickListener(selection -> {
            String selectedDate = new SimpleDateFormat("dd. MM. yyyy.", Locale.getDefault())
                    .format(new Date(selection));
            datePicker.setText(selectedDate);
        });

        cancelButton.setOnClickListener(this);

        confirmButton.setOnClickListener(v -> {
            if (amountInput.getText().toString().isEmpty() || categoryPicker.getSelectedItem() == null) {
                Toast.makeText(context, "Not all fields are filled!", Toast.LENGTH_SHORT).show();
            }
            else {
                Double amount = Double.parseDouble(amountInput.getText().toString());
                String notes = notesInput.getText().toString();
                LocalDate date = LocalDate.parse(datePicker.getText().toString(), java.time.format.DateTimeFormatter.ofPattern("dd. MM. yyyy."));
                Category category = (Category) categoryPicker.getSelectedItem();

                if (amount > 0.0) {
                    if (callback != null) {
                        callback.onDataReceived(amount, notes, date, category.getId());
                    }
                    dismiss();
                } else {
                    Toast.makeText(context, "Amount must be bigger than 0!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
