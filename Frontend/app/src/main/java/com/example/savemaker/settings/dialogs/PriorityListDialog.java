package com.example.savemaker.settings.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.settings.adapters.PriorityListAdapter;
import com.example.savemaker.settings.models.PriorityListElement;
import com.example.savemaker.utils.ClientUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriorityListDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private List<PriorityListElement> elements;
    private PriorityListAdapter adapter;

    public PriorityListDialog(@NonNull Context context, List<PriorityListElement> priorityListElements) {
        super(context);
        this.context = context;
        this.elements = priorityListElements;
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
        setContentView(R.layout.dialog_priority_list);

        adapter = new PriorityListAdapter(context, elements);

        RecyclerView recyclerView = findViewById(R.id.dialog_priority_list_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                adapter.onItemMove(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Button cancelButton = findViewById(R.id.dialog_priority_list_cancel_button);
        cancelButton.setOnClickListener(this);

        Button confirmButton = findViewById(R.id.dialog_priority_list_save_button);
        confirmButton.setOnClickListener(v -> {
            Call<Boolean> call = ClientUtils.settingsService.updatePriorityList(adapter.retrievePriorities());
            call.enqueue(
                    new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.isSuccessful() && response.body() != null && response.body()) {
                                Toast.makeText(context, "Priority list updated successfully!", Toast.LENGTH_SHORT).show();
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
