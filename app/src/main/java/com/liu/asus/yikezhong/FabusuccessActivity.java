package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;

public class FabusuccessActivity extends BaseActivity {


    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_fabusuccess;
    }

    @Override
    public void init() {

    }

    @Override
    public void ondestory() {

    }

    @OnClick(R.id.tv_quxiao)
    public void onViewClicked() {
        finish();
    }
}
