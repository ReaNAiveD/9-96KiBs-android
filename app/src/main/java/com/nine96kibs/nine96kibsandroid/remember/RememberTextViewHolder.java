package com.nine96kibs.nine96kibsandroid.remember;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nine96kibs.nine96kibsandroid.R;

public class RememberTextViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView rememberTextBody;
    private TextView rememberTextSource;

    public RememberTextViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        rememberTextBody = itemView.findViewById(R.id.remember_text_body);
        rememberTextSource = itemView.findViewById(R.id.remember_text_source);
    }

    public void setRememberTextBody(String body) {
        rememberTextBody.setText(body);
    }

    public void setRememberTextSource(String source) {
        rememberTextSource.setText(source);
    }
}
