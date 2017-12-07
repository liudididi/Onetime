package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;

public class FashipingActivity extends BaseActivity {

    @BindView(R.id.iv_luzhi)
    ImageView ivLuzhi;
    @BindView(R.id.img_result)
    RelativeLayout imgResult;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_fashiping;
    }

    @Override
    public void init() {

    }

    @Override
    public void ondestory() {
        PictureFileUtils.deleteCacheDirFile(FashipingActivity.this);
    }

    @OnClick(R.id.iv_luzhi)
    public void onViewClicked() {
        PictureSelector.create(FashipingActivity.this)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .minSelectNum(1)
                .imageSpanCount(3)
                .previewVideo(true)
                .isCamera(true)
                .compress(true)
                .videoQuality(1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    System.out.println("path====="+path);
                    if(path!=null){
                     /*   View inflate = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, imgResult);

                        PlayerView playerVie=new PlayerView(this,inflate)
                                .setTitle("什么")
                                .setScaleType(PlayStateParams.fitparent)
                                .hideMenu(true)
                                .forbidTouch(false)
                                .setPlaySource(path);
                        playerVie.hideBack(true);
                        playerVie.hideRotation(true);
                        playerVie.hideCenterPlayer(false);
                        playerVie.hideFullscreen(true);*/

                       Intent intent=new Intent(FashipingActivity.this,MymapActivity.class);
                        intent.putExtra("videopath",path);
                        startActivity(intent);
                    }else {
                        Toast.makeText(this, "视频获取错误", Toast.LENGTH_SHORT).show();
                    }

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    break;
            }
        }
    }


}
