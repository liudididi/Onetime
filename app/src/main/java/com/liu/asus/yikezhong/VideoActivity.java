package com.liu.asus.yikezhong;

import android.os.Bundle;

import com.dou361.ijkplayer.widget.IjkVideoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mybase.BaseActivity;
import mybase.Basepresent;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.video_view)
    IjkVideoView videoView;

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
        videoView.setVideoPath("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv");
        videoView.start();
    }

    @Override
    public void ondestory() {

    }


}
