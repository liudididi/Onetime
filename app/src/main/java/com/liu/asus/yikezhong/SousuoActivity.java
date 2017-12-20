package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import javax.inject.Inject;

import adapter.SousuoAdapter;
import bean.GuanzhuBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commpont.DaggerSousuocommpont;
import mInterface.Sousuov;
import mybase.BaseActivity;
import mybase.Basepresent;
import mymodules.Sousuomoudule;
import present.Sousuop;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SousuoActivity extends BaseActivity implements Sousuov {
    @Inject
    Sousuop sousuop;
    @BindView(R.id.ss_fanhui)
    TextView ssFanhui;
    @BindView(R.id.ed_sousuo)
    EditText edSousuo;
    @BindView(R.id.tv_sousuo)
    TextView tvSousuo;
    @BindView(R.id.ss_xlist)
    RecyclerView ssXlist;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;

    private int page = 1;
    private SousuoAdapter sousuoAdapter;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_sousuo;
    }

    @Override
    public void init() {
        DaggerSousuocommpont.builder().sousuomoudule(new Sousuomoudule(this)).build().injectSousuo(this);
        ssXlist.setLayoutManager(new LinearLayoutManager(this));
        // 3. 绑定组件
        SearchView searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                sousuop.getsousuodata(string, page + "");
            }
        });


        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });


    }

    @Override
    public void ondestory() {

    }

    @OnClick({R.id.ss_fanhui, R.id.tv_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ss_fanhui:
                finish();
                break;
            case R.id.tv_sousuo:
                sousuop.getsousuodata(edSousuo.getText().toString(), page + "");
                break;
        }
    }

    @Override
    public void getisousuosuccess(List<GuanzhuBean> listBasebean) {
        if(listBasebean!=null&&listBasebean.size()>=1){
            sousuoAdapter = new SousuoAdapter(this, listBasebean);
            ssXlist.setAdapter(sousuoAdapter);
            tvTishi.setVisibility(View.GONE);
        }else {
            sousuoAdapter = new SousuoAdapter(this, listBasebean);
            ssXlist.setAdapter(sousuoAdapter);
            tvTishi.setText("该好友不存在~");
            tvTishi.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getisousuofail(String msg) {


    }


}
