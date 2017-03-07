package com.csdn.scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ScrollerBy extends Activity {

    private float x, y;
    private int mx, my;
    private ImageView testId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.testId = (ImageView) findViewById(R.id.testId);
        testId.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                    case MotionEvent.ACTION_MOVE:
                        mx = (int) (event.getRawX() - x);

                        my = (int) (event.getRawY() - y - 50);
                        //v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
                        v.scrollBy(-mx,-my);
                        break;
                }


                return true;
            }

        });
    }
}
