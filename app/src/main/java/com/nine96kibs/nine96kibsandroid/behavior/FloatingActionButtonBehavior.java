package com.nine96kibs.nine96kibsandroid.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class FloatingActionButtonBehavior extends FloatingActionButton.Behavior {

    private boolean isAnimate;

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE && !isAnimate)
            hide(child);
        else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE && !isAnimate)
            show(child);
    }

    private void hide(View view) {
        view.animate().scaleX(0f).scaleY(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimate = true;
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate= false;
                view.setVisibility(View.INVISIBLE);
            }
        }).start();
    }

    private void show(View view) {
        isAnimate  = true;
        view.animate().scaleX(1f).scaleY(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate= false;
            }
        }).start();
    }

}
