package com.liu.asus.yikezhong;

import com.umeng.analytics.MobclickAgent;

import Base.BaseActivity;
import present.LognP;

public class MainActivity extends BaseActivity<LognP> {

    @Override
    public int getid() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

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
}
