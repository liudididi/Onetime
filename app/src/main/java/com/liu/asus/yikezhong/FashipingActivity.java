package com.liu.asus.yikezhong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;



public class FashipingActivity extends BaseActivity {

    @BindView(R.id.iv_luzhi)
    ImageView ivLuzhi;
    @BindView(R.id.img_result)
    SimpleDraweeView imgResult;
    @BindView(R.id.tv_fanhui)
    TextView tvFanhui;

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
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //设置uri,加载本地的gif资源
                .setUri(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645881499&di=ad19fed6833335094a5632e97cf21710&imgtype=0&src=http%3A%2F%2Fimg1.ph.126.net%2F4xYRue_Ne5iv6f3nJLejGA%3D%3D%2F6631767055097866405.gif"))//设置uri
                .build();
//设置Controller
        imgResult.setController(mDraweeController);
    }

    @Override
    public void ondestory() {
        PictureFileUtils.deleteCacheDirFile(FashipingActivity.this);
    }

    @OnClick({R.id.iv_luzhi, R.id.tv_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_fanhui:
                finish();
                break;
            case R.id.iv_luzhi:
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
                break;
        }


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
                    System.out.println("path=====" + path);
                    if (path != null) {
                        Intent intent = new Intent(FashipingActivity.this, MymapActivity.class);
                        intent.putExtra("videopath", path);
                        startActivity(intent);
                    } else {
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
