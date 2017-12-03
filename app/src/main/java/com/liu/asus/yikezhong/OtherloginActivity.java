package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mInterface.Lognview;
import mybase.BaseActivity;
import mybase.Basepresent;
import present.LognP;
import utils.SPUtils;

public class OtherloginActivity extends BaseActivity implements Lognview {

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.but_log)
    Button butLog;
    @BindView(R.id.tv_ykdl)
    TextView tvYkdl;
    @BindView(R.id.iv_fh1)
    ImageView ivFh1;
    private LognP lognp;

    @Override
    public List<Basepresent> initp() {
        List<Basepresent> list = new ArrayList<>();
        list.add(lognp);
        return list;
    }

    @Override
    public int getid() {
        return R.layout.activity_otherlogin;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        lognp = new LognP(this);
    }

    @Override
    public void ondestory() {

    }

    @OnClick({R.id.but_log, R.id.tv_ykdl,R.id.iv_fh1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_log:
                String pass = etPsd.getText().toString();
                String user = etUser.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast("用户名或密码为空");
                    return;
                }
                lognp.login(etUser.getText().toString(), etPsd.getText().toString());
                break;
            case R.id.tv_ykdl:
                intent(OtherloginActivity.this, MainActivity.class);
                break;
            case R.id.iv_fh1:
                intent(OtherloginActivity.this, LoginActivity.class);
                break;

        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void lognsuess(UserBean userBean) {
        Toast("登录成功");
        SPUtils.put(this, "token", userBean.token);
        SPUtils.put(this, "icon", userBean.icon);
        SPUtils.put(this, "uid", userBean.uid);
        SPUtils.put(this, "nickname", userBean.nickname);
        System.out.println("token===" + userBean.token);
        intent(OtherloginActivity.this, MainActivity.class);
        finish();
    }

    @Override
    public void lognfail(String msg) {
        Toast(msg);
    }

}
