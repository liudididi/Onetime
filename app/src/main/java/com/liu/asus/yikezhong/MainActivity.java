package com.liu.asus.yikezhong;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.meg7.widget.CircleImageView;
import com.meg7.widget.CustomShapeImageView;
import com.umeng.analytics.MobclickAgent;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import adapter.GlideLoader;
import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Celeft;
import fragment.Duanzi;
import fragment.Shiping;
import fragment.Tuijian;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mInterface.Lognview;
import mybase.BaseActivity;
import mybase.Basebean;
import mybase.Basepresent;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import present.LognP;
import utils.MyQusetUtils;
import utils.SPUtils;

public class MainActivity extends BaseActivity implements Lognview, Celeft.Ce_iconback {

    @BindView(R.id.img_biji)
    ImageView imgBiji;
    @BindView(R.id.frame_main)
    FrameLayout frameMain;
    @BindView(R.id.img_tuijian)
    ImageView imgTuijian;
    @BindView(R.id.img_duanzi)
    ImageView imgDuanzi;
    @BindView(R.id.img_shiping)
    ImageView imgShiping;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.tv_duanzi)
    TextView tvDuanzi;
    @BindView(R.id.tv_shiping)
    TextView tvShiping;
    @BindView(R.id.linearlaout)
    LinearLayout linearlaout;
    @BindView(R.id.frame_left)
    FrameLayout frameLeft;
    @BindView(R.id.img_icon)
    CircleImageView imgIcon;
    @BindView(R.id.line_tuijian)
    LinearLayout lineTuijian;
    @BindView(R.id.line_duanzi)
    LinearLayout lineDuanzi;
    @BindView(R.id.line_shiping)
    LinearLayout lineShiping;


    private DrawerLayout dw;
    private LognP lognP;
    private int uid;
    private ArrayList<String> path ;
    private  CircleImageView ce_icon;

    @Override
    public List<Basepresent> initp() {
        List<Basepresent> list = new ArrayList<>();
        list.add(lognP);
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getid() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        path = new ArrayList<>();
        dw = findViewById(R.id.dw);
        lognP = new LognP(this);
        uid = (int) SPUtils.get(this, "uid", 0);
          String token = (String) SPUtils.get(this, "token", "");


        if (uid != 0) {
            lognP.getuser(uid, token);
        } else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(imgIcon);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Tuijian()).commit();
        Celeft celeft = new Celeft();
        celeft.setCe_iconback(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_left, celeft).commit();
        dw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.setClickable(true);

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        // dw.setScrimColor(Color.TRANSPARENT);  去除阴影
    }

    @Override
    public void ondestory() {
      if(path!=null){
          path=null;
      }
    }

    public void onResume() {

        MobclickAgent.onResume(this);

        super.onResume();
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.img_icon, R.id.img_biji,R.id.line_tuijian, R.id.line_duanzi, R.id.line_shiping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_icon:
                FrameLayout rela_left = findViewById(R.id.frame_left);
                dw.openDrawer(rela_left);
                break;
            case R.id.img_biji:
                intent(MainActivity.this, ChuangzuoActivity.class);
                break;
                case R.id.line_tuijian:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Tuijian()).commit();
                    imgTuijian.setImageResource(R.drawable.tuijian2);
                    imgDuanzi.setImageResource(R.drawable.duanzi1);
                    imgShiping.setImageResource(R.drawable.shiping1);
                    tvTuijian.setTextColor(Color.parseColor("#03A9F4"));
                    tvDuanzi.setTextColor(Color.parseColor("#cccccc"));
                    tvShiping.setTextColor(Color.parseColor("#cccccc"));
                    break;
                case R.id.line_duanzi:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Duanzi()).commit();
                    imgTuijian.setImageResource(R.drawable.tuijian1);
                    imgDuanzi.setImageResource(R.drawable.duanzi2);
                    imgShiping.setImageResource(R.drawable.shiping1);
                    tvTuijian.setTextColor(Color.parseColor("#cccccc"));
                    tvDuanzi.setTextColor(Color.parseColor("#03A9F4"));
                    tvShiping.setTextColor(Color.parseColor("#cccccc"));
                    break;
                case R.id.line_shiping:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new Shiping()).commit();
                    imgTuijian.setImageResource(R.drawable.tuijian1);
                    imgDuanzi.setImageResource(R.drawable.duanzi1);
                    imgShiping.setImageResource(R.drawable.shiping2);
                    tvTuijian.setTextColor(Color.parseColor("#cccccc"));
                    tvDuanzi.setTextColor(Color.parseColor("#cccccc"));
                    tvShiping.setTextColor(Color.parseColor("#03A9F4"));
                    break;
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {
        Toast(msg);
        intent(MainActivity.this, LoginActivity.class);
    }

    @Override
    public void lognsuess(UserBean userBean) {
        SPUtils.put(this, "token", userBean.token);

        if (userBean.icon != null && userBean.icon.length() >= 3) {
            SPUtils.put(this, "icon", userBean.icon);
            Glide.with(this).load(userBean.icon)
                     .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .into(imgIcon);
        } else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(imgIcon);
        }
    }

    @Override
    public void lognfail(String msg) {
  // 开启图片选择器




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            path.clear();
            path.addAll(pathList);

            if(pathList.size()>0){
                MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                build.addFormDataPart("uid",uid+"");
                File file=new File(path.get(0));
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                build.addFormDataPart("file", file.getName(), requestFile);
                List<MultipartBody.Part> parts = build.build().parts();
                new MyQusetUtils.Builder().addConverterFactory()
                        .addCallAdapterFactory().build().getQuestInterface().changeicon(parts)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Basebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Basebean value) {
                        if(value.code.equals("0")){
                            Toast(value.msg);
                            SPUtils.put(MainActivity.this,"icon",path.get(0));
                            Glide.with(MainActivity.this).load(path.get(0)).into(ce_icon);
                            Glide.with(MainActivity.this).load(path.get(0)).into(imgIcon);
                        }else {
                            Toast(value.msg);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }




        }

    }

    @Override
    public void Changge(CircleImageView icon) {
        this.ce_icon=icon;
        ImageConfig imageConfig
                = new ImageConfig.Builder(
                // GlideLoader 可用自己用的缓存库
                new GlideLoader())
                // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                .steepToolBarColor(getResources().getColor(R.color.status))
                // 标题的背景颜色 （默认黑色）
                .titleBgColor(getResources().getColor(R.color.status))
                // 提交按钮字体的颜色  （默认白色）
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                // 标题颜色 （默认白色）
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启多选   （默认为多选）  (单选 为 singleSelect)
                .singleSelect()
//                        .crop()
                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(1)
                // 已选择的图片路径
                .pathList(path)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/ImageSelector/Pictures")
                // 开启拍照功能 （默认开启）
                .showCamera()
                .crop()
                .requestCode(101)
                .build();


        ImageSelector.open(MainActivity.this, imageConfig);
    }
}
