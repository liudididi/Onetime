package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;
import utils.SPUtils;

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
    private Tencent mTencent;
    private UserInfo mInfo;
    private QQLoginListener mListener;

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
        String token = (String) SPUtils.get(this, "token", "");
        if(token!=null&&token.length()>3){
            intent(LoginActivity.this,MainActivity.class);
            finish();
        }
        mListener = new QQLoginListener();
        if (mTencent == null) {
            mListener=null;
            mTencent = Tencent.createInstance("1104796216", this);
        }

    }

    @Override
    public void ondestory() {
        if(mTencent!=null){
            mTencent=null;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }

    @OnClick({R.id.rela_qq, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rela_qq:
                if (!mTencent.isSessionValid()) {
                    mTencent.login(this, "all", mListener);
                }
                break;
            case R.id.login_tv:
                intent(LoginActivity.this, OtherloginActivity.class);
                break;
        }
    }

    private class QQLoginListener implements IUiListener {

        @Override
        public void onComplete(Object object) { //登录成功


            Toast("登录成功");
                 /*   //获取openid和token
            initOpenIdAndToken(object);
            //获取用户信息
            getUserInfo();*/
        }

        @Override
        public void onError(UiError uiError) {  //登录失败
            Toast("" + uiError.toString());
        }

        @Override
        public void onCancel() {

            //取消登录
        }
    }
}

