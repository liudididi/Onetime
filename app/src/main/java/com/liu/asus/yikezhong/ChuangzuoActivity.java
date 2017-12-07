package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;

public class ChuangzuoActivity extends BaseActivity {


    @BindView(R.id.tv_quxiao)
    TextView tvQuxiao;
    @BindView(R.id.include)
    RelativeLayout include;
    @BindView(R.id.iv_shipin)
    ImageView ivShipin;
    @BindView(R.id.iv_duanzi)
    ImageView ivDuanzi;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_chuangzuo;
    }

    @Override
    public void init() {


    }

    @Override
    public void ondestory() {

    }

    @OnClick({R.id.tv_quxiao, R.id.iv_shipin, R.id.iv_duanzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quxiao:
                finish();
                break;
            case R.id.iv_shipin:
                intent(ChuangzuoActivity.this,FashipingActivity.class);
                break;
            case R.id.iv_duanzi:
                intent(ChuangzuoActivity.this,FaduanziActivity.class);
                break;
        }
    }
}
