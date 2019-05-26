package com.nine96kibs.nine96kibsandroid.remember;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nine96kibs.nine96kibsandroid.R;

import java.util.List;

public class RememberTextAdapter extends RecyclerView.Adapter<RememberTextViewHolder> {

    private List<RememberText> rememberTexts;

    public RememberTextAdapter(List<RememberText> rememberTexts) {
        this.rememberTexts = rememberTexts;
    }

    @NonNull
    @Override
    public RememberTextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.remember_text, viewGroup, false);
        return new RememberTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RememberTextViewHolder rememberTextViewHolder, int i) {
        RememberText rememberText = rememberTexts.get(i);
        rememberTextViewHolder.setRememberTextBody(rememberText.getBody());
        rememberTextViewHolder.setRememberTextSource(rememberText.getSource());
    }

    @Override
    public int getItemCount() {
        return rememberTexts.size();
    }
}
