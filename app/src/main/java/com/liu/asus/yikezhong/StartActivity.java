package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import Base.BaseActivity;
import Base.Basepresent;

public class StartActivity extends BaseActivity {


    private Handler handler;
    private Runnable task;
    private  int i=3;

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
                 intent(StartActivity.this,MainActivity.class);
              }else {
                  handler.postDelayed(task,1000);
              }
            }
        };
        handler.postDelayed(task,1000);


    }
}