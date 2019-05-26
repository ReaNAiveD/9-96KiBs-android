package com.nine96kibs.nine96kibsandroid.recite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nine96kibs.nine96kibsandroid.R;

public class ReciteTaskViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView reciteTaskName;
    private ProgressBar reciteTaskProgress;

    public ReciteTaskViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        reciteTaskName = itemView.findViewById(R.id.recite_task_name);
        reciteTaskProgress = itemView.findViewById(R.id.recite_task_progress);
    }

    void setOnClickViewListener(View.OnClickListener l) {
        view.setOnClickListener(l);
    }

    void setReciteTaskName(String name) {
        reciteTaskName.setText(name);
    }

    void setReciteTaskProgress(int done, int half, int todo) {
        reciteTaskProgress.setMax(done + half + todo);
        reciteTaskProgress.setSecondaryProgress(done + half);
        reciteTaskProgress.setProgress(done);
    }

}
