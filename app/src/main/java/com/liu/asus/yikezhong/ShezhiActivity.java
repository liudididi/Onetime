package com.liu.asus.yikezhong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;
import utils.ClearCacheUtils;
import utils.SPUtils;

public class ShezhiActivity extends BaseActivity {

    @BindView(R.id.shezhi_rl_clear_huncun)
    RelativeLayout shezhiRlClearHuncun;
    @BindView(R.id.shezhi_login_clear)
    Button shezhiLoginClear;
    @BindView(R.id.she_tv_hcnum)
    TextView sheTvHcnum;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_shezhi;
    }

    @Override
    public void init() {
        try {
            String totalCacheSize = ClearCacheUtils.getdqSize(this);
            sheTvHcnum.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ondestory() {

    }


    @OnClick({R.id.shezhi_rl_clear_huncun, R.id.shezhi_login_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_rl_clear_huncun:
                try {

                    AlertDialog.Builder ad = new AlertDialog.Builder(this)
                            .setTitle("是否清除缓存")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ClearCacheUtils.clearAllCache(ShezhiActivity.this);
                                    try {
                                        ClearCacheUtils.clearAllCache(ShezhiActivity.this);
                                        String    totalCacheSize = ClearCacheUtils.getdqSize(ShezhiActivity.this);
                                        sheTvHcnum.setText(totalCacheSize);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    ad.show();





                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shezhi_login_clear:
                SPUtils.clear(this);
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}
