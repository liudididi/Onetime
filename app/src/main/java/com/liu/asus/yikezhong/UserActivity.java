package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meg7.widget.CircleImageView;
import com.yalantis.ucrop.view.CropImageView;

import java.util.List;

import javax.inject.Inject;

import adapter.Tuijianapter;
import adapter.Useradpter;
import bean.TuijianBean;
import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import commpont.DaggerMaicommpont;
import commpont.DaggerUsercommpont;
import mInterface.Lognview;
import mInterface.Userv;
import mybase.BaseActivity;
import mybase.Basepresent;
import mymodules.Mainmoudule;
import mymodules.Usermoudule;
import present.LognP;
import present.Userp;
import utils.SPUtils;

public class UserActivity extends BaseActivity implements Userv {
   @Inject
    Userp userp;
 /* @Inject
    LognP lognP;*/
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    private Useradpter adpter;
    private  int  page=1;
    private  int  type=0;
    private int uid;
    private String username;
    private String icon;
    private  int zuonum;
    private TextView zuopin_size;

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
        DaggerUsercommpont.builder().usermoudule(new Usermoudule(this)).build().inject(this);
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getExtras();
        icon = bundleExtra.getString("icon");
        uid = bundleExtra.getInt("uid");
        username = bundleExtra.getString("username");
        userp.getdatauser(uid,page);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        View inflate = View.inflate(this, R.layout.userheader, null);
        CircleImageView rouser= inflate.findViewById(R.id.ro_user);
        TextView tv_guanzhu= inflate.findViewById(R.id.tv_guanzhu);
        tv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        zuopin_size = inflate.findViewById(R.id.tv_zuopin_size);
        Glide.with(this).load(icon).into(rouser);
        xrv.addHeaderView(inflate);
        xrv.setRefreshProgressStyle(14);//刷新样式
        xrv.setLaodingMoreProgressStyle(14);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                type=0;
                zuonum=0;
                page=1;
            userp.getdatauser(uid,page);
            }

            @Override
            public void onLoadMore() {
                type=1;
                page++;
          userp.getdatauser(uid,page);
            }
        });



    }

    @Override
    public void ondestory() {

    }
    @Override
    public void getdatasuess(List<TuijianBean> list) {
        zuonum+=list.size();
        zuopin_size.setText("作品("+zuonum+")");
     if(adpter==null){
         adpter = new Useradpter(list,this,username,icon);
         xrv.setAdapter(adpter);
     }else {
         if(type==0){
             adpter.Refresh(list);
             xrv.refreshComplete();
         }else {
             adpter.LoadMore(list);
             xrv.loadMoreComplete();
         }
     }


    }

    @Override
    public void getdatafail(String msg) {

    }
}
