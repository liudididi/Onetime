package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import mybase.BaseActivity;
import mybase.Basepresent;

import static com.liu.asus.yikezhong.Myapp.context;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.tv_fanhui)
    TextView tvFanhui;
    @BindView(R.id.xq_jiecao)
    JCVideoPlayerStandard xqJiecao;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_video;
    }

    @Override
    public void init() {

        Intent intent = getIntent();
        String videourl = intent.getStringExtra("videourl");
        String replace = videourl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        xqJiecao.setUp(replace, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        xqJiecao.startPlayLogic();

    }

    @Override
    public void ondestory() {
        xqJiecao.releaseAllVideos();
        xqJiecao=null;
    }


    @OnClick(R.id.tv_fanhui)
    public void onViewClicked() {
        finish();
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


}
