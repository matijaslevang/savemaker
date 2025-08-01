package com.example.savemaker.common.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savemaker.R;
import com.example.savemaker.settings.adapters.PreferredPriorityAdapter;
import com.example.savemaker.settings.models.PreferredPriorityElement;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.utils.ClientUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDialog extends Dialog implements View.OnClickListener {

    private String title;
    private String content;

    public NoticeDialog(@NonNull Context context, String title, String content) {
        super(context);
        this.title = title;
        this.content = content;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getWindow() != null) {
            getWindow().setLayout(
                    (int)(getContext().getResources().getDisplayMetrics().widthPixels * 0.9),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            getWindow().getAttributes().height = Math.max(getContext().getResources().getDisplayMetrics().heightPixels / 4, getWindow().getAttributes().height);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_notice);

        TextView title = findViewById(R.id.dialog_notice_title);
        TextView content = findViewById(R.id.dialog_notice_content);
        Button button = findViewById(R.id.dialog_notice_ok_button);

        title.setText(this.title);
        content.setText(this.content);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
