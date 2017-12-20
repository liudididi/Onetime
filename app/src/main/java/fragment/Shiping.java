package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.asus.yikezhong.R;
import com.liu.asus.yikezhong.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.SpHotAdapter;
import bean.TuijianBean;
import mInterface.SpHotv;
import mybase.Basefragment;
import mybase.Basepresent;
import present.SpHotp;
import utils.AnimationLoading;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Shiping extends Basefragment implements SpHotv, SpHotAdapter.SpHotBlack {

    private RelativeLayout rela_remen,rela_fujin;
    private TextView tv_remen;
    private TextView tv_fujin;
    private View v_fujin;
    private View v_remen;
    private XRecyclerView sp_remen;
    private SpHotp spHotp;
    private  int repage=1;
    private  int retype;
    private SpHotAdapter remenadpter;
    private Handler handler=new Handler();
    private AnimationLoading ani;

    @Override
    public int getlayoutid() {
        return R.layout.shiping;
    }
    @Override
    public void init() {

        ani = view.findViewById(R.id.ani);
        tv_remen = view.findViewById(R.id.tv_remen);
        tv_fujin = view.findViewById(R.id.tv_fujin);
        v_fujin = view.findViewById(R.id.v_fujin);
        v_remen = view.findViewById(R.id.v_remen);
        //视频xlistview
        spHotp = new SpHotp(this);
        sp_remen = view.findViewById(R.id.sp_remen);
        sp_remen.setRefreshProgressStyle(10);//刷新样式
        sp_remen.setLaodingMoreProgressStyle(10);//加载样式
        spHotp.getSpHotdata(repage);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        sp_remen.setLayoutManager(staggeredGridLayoutManager);

        sp_remen.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        retype=0;
                        repage=1;
                        spHotp.getSpHotdata(repage);

                    }
                }, 500);

            }
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        retype=1;
                        repage++;
                        spHotp.getSpHotdata(repage);
                    }
                }, 500);
            }
        });
        rela_remen = view.findViewById(R.id.rela_remen);
        rela_fujin = view.findViewById(R.id.rela_fujin);
        rela_fujin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_fujin.setTextColor(Color.parseColor("#03A9F4"));
                tv_remen.setTextColor(Color.parseColor("#cccccc"));
                v_fujin.setVisibility(View.VISIBLE);
                v_remen.setVisibility(View.GONE);
            }
        });
        rela_remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "我是视屏的热门", Toast.LENGTH_SHORT).show();
                tv_fujin.setTextColor(Color.parseColor("#cccccc"));
                tv_remen.setTextColor(Color.parseColor("#03A9F4"));
                v_fujin.setVisibility(View.GONE);
                v_remen.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void ondistory() {

    }

    @Override
    public List<Basepresent> inip() {
        List<Basepresent> list=new ArrayList<>();
        list.add(spHotp);
        return list;
    }
    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void getdatasuess(List<TuijianBean> list) {
        for (TuijianBean tuijianBean : list) {
            tuijianBean.hight= (int)(400+(Math.random()*400));
             }
        if(list!=null){
            ani.stopLoading();
            ani.setVisibility(View.GONE);
            sp_remen.setVisibility(View.VISIBLE);
            if(remenadpter==null){
                remenadpter = new SpHotAdapter(list,getActivity());
                remenadpter.setSpHotBlack(this);
                sp_remen.setAdapter(remenadpter);
            }else {
                if(retype==0){
                    remenadpter.Refresh(list);
                    sp_remen .refreshComplete();
                }else {
                    remenadpter.LoadMore(list);
                    sp_remen.loadMoreComplete();
                }
            }
        }else {
            ani.setVisibility(View.VISIBLE);
            sp_remen.setVisibility(View.GONE);
        }
    }

    @Override
    public void getdatafail(String msg) {

    }

    @Override
    public void getvideourl(int  wid) {
        Intent intent=new Intent(getActivity(), VideoActivity.class);
        intent.putExtra("wid",wid);
        startActivity(intent);
    }
}
