package com.nine96kibs.nine96kibsandroid.collect;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nine96kibs.nine96kibsandroid.R;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView info;

    CollectionViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        info = itemView.findViewById(R.id.info);
    }

    void setTag(int key, Object tag) {
        view.setTag(key, tag);
    }

    void setOnClickViewListener(View.OnClickListener l) {
        view.setOnClickListener(l);
    }

    void setInfo(String s) {
        info.setText(s);
    }

}
