package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import mybase.BaseActivity;
import mybase.Basepresent;


public class StartActivity extends BaseActivity {


    private Handler handler;
    private Runnable task;
    private  int i=2;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getid() {
        initztl("#00ff00");
        return R.layout.activity_start;
    }

    @Override
    public void init() {
        setStatus(true);
        handler = new Handler();
        task = new Runnable() {
            @Override
            public void run() {
              i--;
              if(i==0){
                 intent(StartActivity.this,LoginActivity.class);
                 finish();
              }else {
                  handler.postDelayed(task,500);
              }
            }
        };
        handler.postDelayed(task,500);


    }

    @Override
    public void ondestory() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null){
            handler.removeCallbacks(task);
              task=null;
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                               View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
