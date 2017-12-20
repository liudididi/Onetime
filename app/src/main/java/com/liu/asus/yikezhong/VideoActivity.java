package com.liu.asus.yikezhong;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import adapter.PinglunAdapter;
import bean.TuijianBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commpont.DaggerXiangqcommpont;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import mInterface.Xqview;
import mybase.BaseActivity;
import mybase.Basepresent;
import mybase.Baseview;
import mymodules.Xiangqmoudule;
import present.Pinglunp;
import present.Xqpresent;
import utils.SPUtils;

public class VideoActivity extends BaseActivity implements Xqview, Baseview {

    @Inject
    Xqpresent xqpresent;
    @BindView(R.id.tv_fanhui)
    ImageView tvFanhui;
    @BindView(R.id.xq_jiecao)
    JCVideoPlayerStandard xqJiecao;
    @BindView(R.id.pl_xlist)
    RecyclerView plXlist;
    @BindView(R.id.xq_icon)
    CircleImageView xqIcon;
    @BindView(R.id.xq_count)
    TextView xqCount;
    @BindView(R.id.xq_imgzhan)
    ImageView xqImgzhan;
    @BindView(R.id.xq_tishi)
    TextView xqTishi;
    @BindView(R.id.xq_fabiao)
    ImageView xqFabiao;
    @BindView(R.id.xq_edpl)
    EditText xqEdpl;


    private Pinglunp pinglunp;
    private int wid;
    private int uid;
    private PinglunAdapter pinglunAdapte;
    private String replace;

    @Override
    public List<Basepresent> initp() {
        List<Basepresent> list = new ArrayList<>();
        list.add(pinglunp);
        return list;
    }

    @Override
    public int getid() {
        return R.layout.activity_video;
    }

    @Override
    public void init() {

        Intent intent = getIntent();
        uid = (int) SPUtils.get(this, "uid", 0);
        wid = intent.getIntExtra("wid", 0);
        pinglunp = new Pinglunp(this);
        DaggerXiangqcommpont.builder().xiangqmoudule(new Xiangqmoudule(this)).build().injectXq(this);
        if (wid != 0) {
            xqpresent.GetXiangqing(wid);
        }
        plXlist.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void ondestory() {

        xqJiecao.release();
        xqJiecao.releaseAllVideos();
        xqJiecao = null;

    }


    @Override
    public void onBackPressed() {
        if (xqJiecao.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        xqJiecao.releaseAllVideos();
    }


    @Override
    public void getdatasuccess(TuijianBean tuijianBean) {


        if(replace ==null){
            replace = tuijianBean.videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
            xqJiecao.setUp(replace, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
            xqJiecao.startPlayLogic();
        }
        if (tuijianBean.comments.size() >= 1) {
            plXlist.setVisibility(View.VISIBLE);
            xqImgzhan.setVisibility(View.GONE);
            xqTishi.setVisibility(View.GONE);
            if(pinglunAdapte==null){
                pinglunAdapte = new PinglunAdapter(this, tuijianBean.comments);
                plXlist.setAdapter(pinglunAdapte);
            }else {
                pinglunAdapte.Refresh(tuijianBean.comments);
            }
        } else {
            xqImgzhan.setVisibility(View.VISIBLE);
            xqTishi.setVisibility(View.VISIBLE);
            plXlist.setVisibility(View.GONE);
        }
        if (tuijianBean.user.icon != null && tuijianBean.user.icon.length() > 3) {
            Glide.with(this).load(tuijianBean.user.icon).into(xqIcon);
        }
        xqCount.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        if(!TextUtils.isEmpty(tuijianBean.workDesc)){
            xqCount.setText(tuijianBean.workDesc);
        }

    }

    @Override
    public void getdatafainl(String msg) {
        Toast(msg);
    }


    @OnClick({R.id.tv_fanhui, R.id.xq_fabiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fanhui:
                finish();
                break;
            case R.id.xq_fabiao:

                if (uid == 0) {
                    Toast("用户未登录，请先登录");
                    return;
                }

                if(TextUtils.isEmpty(xqEdpl.getText().toString())){
                    Toast("评论内容不能为空");
                    return;
                }
                pinglunp.Pinglun(uid, wid, xqEdpl.getText().toString());

                break;
        }
    }

    @Override
    public void success() {
        Toast("发表成功");
        xqEdpl.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(imm.isActive()){
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        xqpresent.GetXiangqing(wid);
    }

    @Override
    public void fail(String msg) {
           Toast(msg);
           if(msg.equals("token超时")){
                   AlertDialog.Builder builder = new AlertDialog.Builder(this);
                   builder.setTitle("登录信息失效");
                   builder.setNegativeButton("取消", null);
                   builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Intent intent=new Intent(VideoActivity.this, LoginActivity.class);
                           SPUtils.clear(VideoActivity.this);
                           startActivity(intent);
                       }
                   });
                   builder.create().show();
           }
    }

}
