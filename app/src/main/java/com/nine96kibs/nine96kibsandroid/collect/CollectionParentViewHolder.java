package com.nine96kibs.nine96kibsandroid.collect;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.nine96kibs.nine96kibsandroid.R;

public class CollectionParentViewHolder extends CollectionViewHolder {

    private ImageView arrow;

    CollectionParentViewHolder(@NonNull View itemView) {
        super(itemView);
        arrow = itemView.findViewById(R.id.arrow);
    }

    void tagArrow(int key, Object tag) {
        arrow.setTag(key, tag);
    }

}
