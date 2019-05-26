package com.nine96kibs.nine96kibsandroid.recite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nine96kibs.nine96kibsandroid.R;

import java.util.List;

public class ReciteTaskAdapter extends RecyclerView.Adapter<ReciteTaskViewHolder> {

    private List<ReciteTask> reciteTasks;

    public ReciteTaskAdapter(List<ReciteTask> reciteTasks) {
        this.reciteTasks = reciteTasks;
    }

    @NonNull
    @Override
    public ReciteTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recite_task, viewGroup, false);
        final ReciteTaskViewHolder reciteTaskViewHolder = new ReciteTaskViewHolder(view);
        reciteTaskViewHolder.setOnClickViewListener(v -> {
            Intent intent = new Intent(viewGroup.getContext(), ReciteActivity.class);
            viewGroup.getContext().startActivity(intent);
        });
        return reciteTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReciteTaskViewHolder reciteTaskViewHolder, int i) {
        ReciteTask reciteTask = reciteTasks.get(i);
        reciteTaskViewHolder.setReciteTaskName(reciteTask.getName());
        reciteTaskViewHolder.setReciteTaskProgress(reciteTask.getDone(), reciteTask.getHalf(), reciteTask.getTodo());
    }

    @Override
    public int getItemCount() {
        return reciteTasks.size();
    }
}
