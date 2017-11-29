package com.liu.asus.yikezhong;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mybase.BaseActivity;
import mybase.Basepresent;
import mybase.Baseview;
import present.Fabiaop;
import utils.SPUtils;

public class FaduanziActivity extends BaseActivity implements Baseview {

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
        if(caogao !=null&& caogao.length()>=2){
            edFabiao.setText(caogao);
        }
        pop_bubaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.remove(FaduanziActivity.this,"caogao");
                finish();
            }
        });
        pop_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SPUtils.put(FaduanziActivity.this,"caogao",edFabiao.getText().toString());
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

    }

    @Override
    public void ondestory() {

    }

    @OnClick({R.id.tv_duanzi_quxiao, R.id.tv_fabiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_duanzi_quxiao:
                if(TextUtils.isEmpty(edFabiao.getText().toString())){
                    finish();
                }else {
                    showPopupWindow();
                }

                break;
            case R.id.tv_fabiao:
                if (uid != 0) {
                    fabiaop.fabiao(uid, edFabiao.getText().toString());
                } else {
                    Toast("请先去登录");
                }
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
    public void success() {
        Toast("发表成功");
    }

    @Override
    public void fail(String msg) {
        Toast(msg);
        SPUtils.remove(this, "token");
        intent(FaduanziActivity.this, LoginActivity.class);
    }

}
