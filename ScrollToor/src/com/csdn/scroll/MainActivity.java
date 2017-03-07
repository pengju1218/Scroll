package com.csdn.scroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {



    private android.widget.Button teset1;
    private android.widget.Button teset2;
    private Button teset0;
    private Button teset3;
    private Button teset4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.teset4 = (Button) findViewById(R.id.teset4);
        this.teset3 = (Button) findViewById(R.id.teset3);
        this.teset0 = (Button) findViewById(R.id.teset0);
        this.teset2 = (Button) findViewById(R.id.teset2);
        this.teset1 = (Button) findViewById(R.id.teset1);
        teset1.setOnClickListener(this);
        teset2.setOnClickListener(this);
        teset0.setOnClickListener(this);
        teset3.setOnClickListener(this);
        teset4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.teset0){
            Intent intet=new Intent(MainActivity.this,Layout.class);
            startActivity(intet);
        }else if(v.getId()==R.id.teset1){
            Intent intet=new Intent(MainActivity.this,ScrollerBy.class);
            startActivity(intet);
        }else if(v.getId()==R.id.teset2){
            Intent intet=new Intent(MainActivity.this,Scroller.class);
            startActivity(intet);
        }else if(v.getId()==R.id.teset3){
            Intent intet=new Intent(MainActivity.this,Animator.class);
            startActivity(intet);
        }else if(v.getId()==R.id.teset4){
            Intent intet=new Intent(MainActivity.this,ViewDragHelper.class);
            startActivity(intet);
        }
    }
}
