package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import Base.BaseActivity;
import Base.Basepresent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.rela_qq)
    RelativeLayout relaQq;
    @BindView(R.id.login_tv)
    TextView loginTv;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rela_qq)
    public void onViewClicked() {
       Toast("登录QQ");


    }
}
