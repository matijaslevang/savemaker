package com.example.savemaker.settings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.settings.models.PreferredPriorityElement;
import com.example.savemaker.transactions.models.Category;

import java.util.List;

public class PreferredPriorityAdapter extends RecyclerView.Adapter<PreferredPriorityAdapter.PreferredPriorityViewHolder> {

    private Context context;
    private List<PreferredPriorityElement> elements;
    private LayoutInflater layoutInflater;
    private List<Category> incomeCategories;

    public PreferredPriorityAdapter(Context context, List<PreferredPriorityElement> elements, List<Category> incomeCategories) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.elements = elements;
        this.incomeCategories = incomeCategories;
    }


    @NonNull
    @Override
    public PreferredPriorityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_holder_preferred_priority_element, parent, false);
        return new PreferredPriorityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferredPriorityViewHolder holder, int position) {
        PreferredPriorityElement element = elements.get(position);
        holder.categoryName.setText(element.getSpendingCategoryName());
        ArrayAdapter<Category> spinnerAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                incomeCategories
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.preferredIncomeCategory.setAdapter(spinnerAdapter);

        if (element.getPreferredIncomeCategory() != null) {
            int selectedIndex = incomeCategories.indexOf(element.getPreferredIncomeCategory());
            if (selectedIndex >= 0) {
                holder.preferredIncomeCategory.setSelection(selectedIndex);
            }
        }

        holder.preferredIncomeCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                element.setPreferredIncomeCategory(incomeCategories.get(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public List<PreferredPriorityElement> retrieveList() {
        return elements;
    }

    public static class PreferredPriorityViewHolder extends RecyclerView.ViewHolder {

        Spinner preferredIncomeCategory;
        TextView categoryName;

        public PreferredPriorityViewHolder(@NonNull View itemView) {
            super(itemView);
            preferredIncomeCategory = itemView.findViewById(R.id.ppe_view_holder_spinner);
            categoryName = itemView.findViewById(R.id.ppe_view_holder_category_name);
        }
    }
}
