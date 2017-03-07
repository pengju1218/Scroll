package com.csdn.scroll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.csdn.scroll.view.ViewPager;

public class Scroller extends Activity {


    private Context mContext;
    private int[] imagesArray;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mContext = this;
        imagesArray = new int[]{R.drawable.a, R.drawable.b};
        viewPager = new ViewPager(mContext);
        ImageView imageView = null;
        RelativeLayout.LayoutParams layoutParams = null;
        for (int i = 0; i < imagesArray.length; i++) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imagesArray[i]);
            layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
            imageView.setLayoutParams(layoutParams);
            viewPager.addView(imageView);
        }
        setContentView(viewPager);
    }


}
