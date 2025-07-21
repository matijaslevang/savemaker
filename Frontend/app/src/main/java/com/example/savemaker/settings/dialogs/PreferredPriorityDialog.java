package com.example.savemaker.settings.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.settings.adapters.PreferredPriorityAdapter;
import com.example.savemaker.settings.adapters.PriorityListAdapter;
import com.example.savemaker.settings.models.PreferredPriorityElement;
import com.example.savemaker.settings.models.PriorityListElement;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.utils.ClientUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferredPriorityDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private List<PreferredPriorityElement> elements;
    private List<Category> categories;
    private PreferredPriorityAdapter adapter;

    public PreferredPriorityDialog(@NonNull Context context, List<PreferredPriorityElement> priorityListElements, List<Category> categories) {
        super(context);
        this.context = context;
        this.elements = priorityListElements;
        this.categories = categories;
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
        setContentView(R.layout.dialog_preferred_priority);

        adapter = new PreferredPriorityAdapter(context, elements, categories);

        RecyclerView recyclerView = findViewById(R.id.dialog_preferred_priority_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Button cancelButton = findViewById(R.id.dialog_preferred_priority_cancel_button);
        cancelButton.setOnClickListener(this);

        Button confirmButton = findViewById(R.id.dialog_preferred_priority_save_button);
        confirmButton.setOnClickListener(v -> {
            Call<Boolean> call = ClientUtils.settingsService.updatePreferredPriorityList(adapter.retrieveList());
            call.enqueue(
                    new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful() && response.body() != null && response.body()) {
                                Toast.makeText(context, "Preferred priorities updated successfully!", Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    }
            );
        });
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
