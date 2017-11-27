package com.liu.asus.yikezhong;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Celeft;
import fragment.Duanzi;
import fragment.Shiping;
import fragment.Tuijian;
import mInterface.Lognview;
import mybase.BaseActivity;
import mybase.Basepresent;
import present.LognP;

public class MainActivity extends BaseActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.img_biji)
    ImageView imgBiji;
    @BindView(R.id.frame_main)
    FrameLayout frameMain;
    @BindView(R.id.img_tuijian)
    ImageView imgTuijian;
    @BindView(R.id.img_duanzi)
    ImageView imgDuanzi;
    @BindView(R.id.img_shiping)
    ImageView imgShiping;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.tv_duanzi)
    TextView tvDuanzi;
    @BindView(R.id.tv_shiping)
    TextView tvShiping;
    @BindView(R.id.linearlaout)
    LinearLayout linearlaout;
    @BindView(R.id.frame_left)
    FrameLayout frameLeft;

    private DrawerLayout dw;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getid() {
        initztl("#03A9F4");
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        dw = findViewById(R.id.dw);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Tuijian()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_left,new Celeft()).commit();
        dw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.setClickable(true);

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
       dw.setScrimColor(Color.TRANSPARENT);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_icon, R.id.img_biji, R.id.img_tuijian, R.id.img_duanzi, R.id.img_shiping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_icon:
                FrameLayout rela_left = findViewById(R.id.frame_left);
                dw.openDrawer(rela_left);
                break;
            case R.id.img_biji:


                break;

            case R.id.img_tuijian:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Tuijian()).commit();
                imgTuijian.setImageResource(R.drawable.tuijian2);
                imgDuanzi.setImageResource(R.drawable.duanzi1);
                imgShiping.setImageResource(R.drawable.shiping1);
                tvTuijian.setTextColor(Color.parseColor("#03A9F4"));
                tvDuanzi.setTextColor(Color.parseColor("#cccccc"));
                tvShiping.setTextColor(Color.parseColor("#cccccc"));

                break;
            case R.id.img_duanzi:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Duanzi()).commit();
                imgTuijian.setImageResource(R.drawable.tuijian1);
                imgDuanzi.setImageResource(R.drawable.duanzi2);
                imgShiping.setImageResource(R.drawable.shiping1);
                tvTuijian.setTextColor(Color.parseColor("#cccccc"));
                tvDuanzi.setTextColor(Color.parseColor("#03A9F4"));
                tvShiping.setTextColor(Color.parseColor("#cccccc"));
                break;
            case R.id.img_shiping:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Shiping()).commit();
                imgTuijian.setImageResource(R.drawable.tuijian1);
                imgDuanzi.setImageResource(R.drawable.duanzi1);
                imgShiping.setImageResource(R.drawable.shiping2);
                tvTuijian.setTextColor(Color.parseColor("#cccccc"));
                tvDuanzi.setTextColor(Color.parseColor("#cccccc"));
                tvShiping.setTextColor(Color.parseColor("#03A9F4"));
                break;
        }
    }
}
