package com.example.savemaker.reports.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.savemaker.reports.ChartReportFragment;
import com.example.savemaker.reports.SimpleReportFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SimpleReportFragment();
            case 1:
                return new ChartReportFragment();
            default:
                throw new IllegalStateException("Invalid tab index");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
