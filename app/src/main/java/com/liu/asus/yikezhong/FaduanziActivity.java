package com.liu.asus.yikezhong;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.Adapter;
import adapter.GlideLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mInterface.Faduanziv;
import mybase.BaseActivity;
import mybase.Basepresent;
import mybase.Baseview;
import present.Fabiaop;
import utils.SPUtils;

public class FaduanziActivity extends BaseActivity implements Faduanziv, Adapter.Imgdele {
    @BindView(R.id.img_add)
    ImageView imgAdd;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private ArrayList<String> path = new ArrayList<>();
    private Adapter adapter;
    @BindView(R.id.tv_duanzi_quxiao)
    TextView tvDuanziQuxiao;
    @BindView(R.id.tv_fabiao)
    TextView tvFabiao;

    @BindView(R.id.ed_fabiao)
    EditText edFabiao;
    @BindView(R.id.rezheng)
    RelativeLayout rezheng;
    private Fabiaop fabiaop;
    private int uid;
    private PopupWindow mPopWindow;
    private TextView pop_bubaocun, pop_finish, pop_baocun;
    private String caogao;

    @Override
    public List<Basepresent> initp() {
        List<Basepresent> list = new ArrayList<>();
        list.add(fabiaop);
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_faduanzi;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        fabiaop = new Fabiaop(this);
        uid = (int) SPUtils.get(this, "uid", 0);
        View contentView = View.inflate(this, R.layout.popw_layout, null);
        mPopWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pop_bubaocun = contentView.findViewById(R.id.pop_bubaocun);
        pop_baocun = contentView.findViewById(R.id.pop_baocun);
        pop_finish = contentView.findViewById(R.id.pop_finish);
        caogao = (String) SPUtils.get(this, "caogao", "");
        if (caogao != null && caogao.length() >= 2) {
            edFabiao.setText(caogao);
        }
        pop_bubaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.remove(FaduanziActivity.this, "caogao");
                finish();
            }
        });
        pop_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.put(FaduanziActivity.this, "caogao", edFabiao.getText().toString());
                finish();
            }
        });

        pop_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable dw = new ColorDrawable(00000000);
                rezheng.setBackgroundDrawable(dw);
                mPopWindow.dismiss();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycle.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        adapter.setImgdele(this);
        recycle.setAdapter(adapter);



    }

    @Override
    public void ondestory() {

    }

    @OnClick({R.id.tv_duanzi_quxiao, R.id.tv_fabiao, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_duanzi_quxiao:
                if (TextUtils.isEmpty(edFabiao.getText().toString())) {
                    finish();
                } else {
                    showPopupWindow();
                }

                break;
            case R.id.tv_fabiao:
                if (uid != 0) {
                    Toast("a");
                        fabiaop.fabiao(uid, edFabiao.getText().toString(),path);
                } else {
                    Toast("请先去登录");
                }
                break;
            case R.id.img_add:
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
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(100)
                        .build();


                ImageSelector.open(FaduanziActivity.this, imageConfig);   // 开启图片选择器

                break;
        }
    }



    private void showPopupWindow() {
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_faduanzi, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        rezheng.setBackgroundDrawable(dw);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }
            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }


    @Override
    public void imgdele(ImageView view,int postion) {
         if(path.get(postion)!=null){
             path.remove(postion);
             adapter.notifyDataSetChanged();
         }
    }

    @Override
    public void fabusuccess() {
        Toast("发表成功");
        edFabiao.setText("");
        path.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fabufail(String msg) {
        Toast(msg);
    }

    @Override
    public void tokenout(String msg) {
        Toast(msg);
        SPUtils.remove(this, "token");
        intent(FaduanziActivity.this, LoginActivity.class);
    }
}
