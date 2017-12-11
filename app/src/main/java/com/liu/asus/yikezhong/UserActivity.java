package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yalantis.ucrop.view.CropImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mybase.BaseActivity;
import mybase.Basepresent;

public class UserActivity extends BaseActivity {


    @BindView(R.id.xrv)
    XRecyclerView xrv;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_user;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra("bundle");
        xrv.setLayoutManager(new LinearLayoutManager(this));
        View inflate = View.inflate(this, R.layout.userheader, null);
        CropImageView  rouser= inflate.findViewById(R.id.ro_user);
        Glide.with(this).load(bundleExtra.getString("icon")).into(rouser);
        xrv.addHeaderView(inflate);




    }

    @Override
    public void ondestory() {

    }


}
