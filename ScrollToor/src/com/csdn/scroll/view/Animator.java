package com.csdn.scroll.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AItsuki on 2016/3/2.
 */
public class Animator extends View {

    private float startX;
    private float startY;

    public Animator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                startY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getRawX();
                float endY = event.getRawY();
                float dx = endX - startX;
                float dy = endY - startY;

               // ObjectAnimator.ofFloat(this, "translationX", getTranslationX() + dx).setDuration(0).start();
                ObjectAnimator.ofFloat(this, "translationY", getTranslationY() + dy).setDuration(0).start();

                startX = endX;
                startY = endY;
                break;
        }
        return true;
    }

}