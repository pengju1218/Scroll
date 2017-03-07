package com.csdn.scroll.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csdn.scroll.R;

/**
 * Created by Administrator on 2015/8/12.
 */
public class DragLayout extends LinearLayout {

    private ViewDragHelper mDragger;

    private ViewDragHelper.Callback callback;

    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected void onFinishInflate() {
        iv1 = (ImageView) this.findViewById(R.id.iv1);
        iv2 = (ImageView) this.findViewById(R.id.iv2);
        super.onFinishInflate();

    }

    public DragLayout(Context context) {
        super(context);

    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        callback = new DraggerCallBack();
        //第二个参数就是滑动灵敏度的意思 可以随意设置
        mDragger = ViewDragHelper.create(this, 1.0f, callback);

    }

    class DraggerCallBack extends ViewDragHelper.Callback {

        //这个地方实际上函数返回值为true就代表可以滑动 为false 则不能滑动
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (child == iv2) {
                return false;
            }
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //决定是否拦截当前事件
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理事件
        mDragger.processTouchEvent(event);
        return true;
    }


}