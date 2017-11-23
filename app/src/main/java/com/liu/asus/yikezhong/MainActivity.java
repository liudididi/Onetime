package com.liu.asus.yikezhong;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import Base.BaseActivity;
import Base.Basepresent;
import MyInterface.Lognview;
import present.LognP;

public class MainActivity extends BaseActivity implements Lognview {
   private  LognP     lognp;

    @Override
    public List<Basepresent> initp() {
           List<Basepresent> list=new ArrayList<>();
          lognp=new LognP(this);
          list.add(lognp);
            return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getid() {
        initztl("#03A9F4");
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        lognp=new LognP(this);
          Toast("你好，再见");
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void lognsuess() {

    }
}
