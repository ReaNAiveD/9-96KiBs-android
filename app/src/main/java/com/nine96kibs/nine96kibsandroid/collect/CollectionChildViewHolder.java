package com.nine96kibs.nine96kibsandroid.collect;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.nine96kibs.nine96kibsandroid.R;

class CollectionChildViewHolder extends CollectionViewHolder {

    private ImageView light;

    CollectionChildViewHolder(@NonNull View itemView) {
        super(itemView);
        light = itemView.findViewById(R.id.light);
    }

    void setLight() {
        double number = Math.random();
        if (number <= 0.33) {
            light.setImageResource(R.drawable.ic_red);
        } else if (number <= 0.66) {
            light.setImageResource(R.drawable.ic_yellow);
        } else {
            light.setImageResource(R.drawable.ic_green);
        }
    }

}
