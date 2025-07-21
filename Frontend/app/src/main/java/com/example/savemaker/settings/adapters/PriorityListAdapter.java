package com.example.savemaker.settings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.settings.models.PriorityListElement;

import java.util.Collections;
import java.util.List;

public class PriorityListAdapter extends RecyclerView.Adapter<PriorityListAdapter.PriorityListViewHolder> {

    private List<PriorityListElement> priorityList;
    private LayoutInflater layoutInflater;

    public PriorityListAdapter(Context context, List<PriorityListElement> priorityList) {
        this.priorityList = priorityList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PriorityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_holder_priority_list_element, parent, false);
        return new PriorityListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityListViewHolder holder, int position) {
        PriorityListElement element = priorityList.get(position);
        holder.id = element.getIncomeTypeBalanceId();
        holder.priority = element.getPriority();
        holder.categoryName.setText(element.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return priorityList.size();
    }

    private void swapPriorities(int positionOne, int positionTwo) {
        PriorityListElement one = priorityList.get(positionOne);
        PriorityListElement two = priorityList.get(positionTwo);
        Integer temp = one.getPriority();
        one.setPriority(two.getPriority());
        two.setPriority(temp);
    }

    public void onItemMove(int fromPosition, int toPosition) {
        swapPriorities(fromPosition, toPosition);
        Collections.swap(priorityList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<PriorityListElement> retrievePriorities() {
        return priorityList;
    }

    public static class PriorityListViewHolder extends RecyclerView.ViewHolder {

        Long id;
        Integer priority;
        TextView categoryName;
        public PriorityListViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.ple_view_holder_category_name);
        }
    }
}
