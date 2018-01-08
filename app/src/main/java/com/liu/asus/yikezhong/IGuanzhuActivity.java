package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

import adapter.IguanzhuAdapter;
import bean.GuanzhuBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commpont.DaggerIguanzhucommpont;
import mInterface.IGuanzhuv;
import mybase.BaseActivity;
import mybase.Basepresent;
import mymodules.IGuanzhumoudule;
import present.IGuanzhup;
import utils.SPUtils;


public class IGuanzhuActivity extends BaseActivity implements IGuanzhuv {
    @Inject
    IGuanzhup iGuanzhup;
    @BindView(R.id.xlv_gaunzhu)
    RecyclerView xlvGaunzhu;
    @BindView(R.id.gz_fanhui)
    TextView gzFanhui;
    @BindView(R.id.igz_title)
    RelativeLayout igzTitle;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_iguanzhu;
    }

    @Override
    public void init() {

        int uid = (int) SPUtils.get(this, "uid", 0);
        DaggerIguanzhucommpont.builder().iGuanzhumoudule(new IGuanzhumoudule(this)).build().injectIguan(this);
        xlvGaunzhu.setLayoutManager(new LinearLayoutManager(this));
        iGuanzhup.getIguanzhudata(uid);
    }

    @Override
    public void ondestory() {

    }

    @Override
    public void getiguanzhusuccess(List<GuanzhuBean> listBasebean) {

        IguanzhuAdapter iguanzhuAdapter = new IguanzhuAdapter(this, listBasebean);
        xlvGaunzhu.setAdapter(iguanzhuAdapter);

    }


    @Override
    public void getiguanzhufail(String msg) {

    }



    @OnClick({R.id.gz_fanhui, R.id.igz_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gz_fanhui:
                finish();
                break;
            case R.id.igz_title:
                break;
        }
    }
}
