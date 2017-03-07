package com.csdn.scroll.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by AItsuki on 2016/3/3.
 */
public class SimpleDrawerLayout extends FrameLayout {

    private final ViewDragHelper mDragHelper;
    private View mContent;
    private int mMenuWidth;

    public SimpleDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, mCallBack);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuWidth = w / 3 * 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    private ViewDragHelper.Callback mCallBack = new ViewDragHelper.Callback() {

        // 触摸到View的时候就会回调这个方法。
        // return true表示抓取这个View。
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mContent == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            return left > 0 ? left > mMenuWidth ? mMenuWidth : left : 0;  // 只能右划出菜单,并且菜单最大宽度为屏幕3分之2
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if (xvel > 300) {
                open();
            } else if (xvel < -300) {
                close();
            } else {
                if (mContent.getLeft() > mMenuWidth / 2) {
                    open();
                } else {
                    close();
                }
            }
        }
    };

    private void close() {
        mDragHelper.smoothSlideViewTo(mContent, 0, 0);
        invalidate();
    }

    private void open() {
        mDragHelper.smoothSlideViewTo(mContent, mMenuWidth, 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}