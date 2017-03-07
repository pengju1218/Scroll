package com.csdn.scroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

public class ViewPager extends ViewGroup {
    private int lastX;
    private int currentX;
    private int distanceX;
    private Context mContext;
    private Scroller mScroller;
    public ViewPager(Context context) {
        super(context);
        mContext=context;
        mScroller=new Scroller(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPager(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    /**
     * 注意:
     * 1 getWidth()和getHeight()得到是屏幕的宽和高
     *   因为在布局时指定了该控件的宽和高为fill_parent
     * 2 view.getScrollX(Y)()得打mScrollX(Y)
     * 3 调用scrollTo(x, y)后,x和y分别被赋值给mScrollX和mScrollY
     *   请注意坐标方向.
     */
    @Override
    protected void onLayout(boolean arg0, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.layout(i*getWidth(), 0,getWidth()+ i*getWidth(),getHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX=(int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX=(int) event.getX();
                distanceX=currentX-lastX;
                mScroller.startScroll(getScrollX(), 0, -distanceX, 0);
                break;
            case MotionEvent.ACTION_UP:
                //手指从屏幕右边往左滑动,手指抬起时滑动到下一屏
                if (distanceX<0&&Math.abs(distanceX)>50) {
                    mScroller.startScroll(getScrollX(), 0, getWidth()-(getScrollX()%getWidth()), 0);
                    //手指从屏幕左边往右滑动,手指抬起时滑动到上一屏
                } else if (distanceX>0&&Math.abs(distanceX)>50) {
                    mScroller.startScroll(getScrollX(), 0, -(getScrollX()%getWidth()), 0);
                }
                break;

            default:
                break;
        }
        //重绘View树
        invalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }else{
            if (mScroller.getCurrX()==getWidth()*(getChildCount()-1)) {
                Toast.makeText(mContext, "已滑动到最后一屏", Toast.LENGTH_SHORT).show();
            }
        }
    }

}