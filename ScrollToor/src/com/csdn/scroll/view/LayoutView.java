package com.csdn.scroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by AItsuki on 2016/3/1.
 */
public class LayoutView extends View {

    private int startX;
    private int startY;

    public LayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) event.getX();
                int endY = (int) event.getY();
                int dx = endX- startX;
                int dy = endY - startY;
                layout(getLeft() + dx, getTop() + dy, getRight() + dx, getBottom() + dy);
                break;
        }
        return true;
    }
}