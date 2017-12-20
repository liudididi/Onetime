package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.asus.yikezhong.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import adapter.Tuijianapter;
import bean.Duanzibean;
import bean.Guanggao;
import bean.TuijianBean;
import mInterface.Duanziview;
import mInterface.Getadv;
import mInterface.Tuijianview;
import mybase.Basefragment;
import mybase.Basepresent;
import present.Getadp;
import present.Getdatap;
import present.Gettuiianp;
import utils.AnimationLoading;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Tuijian  extends Basefragment implements XBanner.XBannerAdapter,Tuijianview, Getadv {


    private RelativeLayout rela_remen,rela_guanzhu;
    private TextView tv_remen;
    private TextView tv_guanzhu;
    private View v_guanzhu;
    private View v_remen;
    private XRecyclerView xlist_tuijian,xlist_guanzhu;
    private List<String> imgesUrl;
    private Getadp getadp;
    private Gettuiianp gettuiianp;
    private Tuijianapter tuijianapter;
    private static  Handler handler=new Handler();
    private  int   ttype;
    private  int   tpage;
    private XBanner mBanner;
    private AnimationLoading ani;

    @Override
    public int getlayoutid() {
        return R.layout.tuijian;
    }

    @Override
    public void init() {
        getadp=new Getadp(this);
        gettuiianp=new Gettuiianp(this);
        ani = view.findViewById(R.id.ani);
        tv_remen = view.findViewById(R.id.tv_remen);
        tv_guanzhu = view.findViewById(R.id.tv_guanzhu);
        v_guanzhu = view.findViewById(R.id.v_guanzhu);
        v_remen = view.findViewById(R.id.v_remen);
        rela_remen = view.findViewById(R.id.rela_remen);
        rela_guanzhu = view.findViewById(R.id.rela_guanzhu);
        xlist_tuijian = view.findViewById(R.id.xlist_tuijian);
        xlist_tuijian.setLayoutManager(new LinearLayoutManager(getActivity()));
        xlist_tuijian.setRefreshProgressStyle(14);//刷新样式
        xlist_tuijian.setLaodingMoreProgressStyle(14);//加载样式
        xlist_tuijian.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ttype=0;
                        tpage=1;
                        gettuiianp.getduanzidata("",1,tpage);

                    }
                }, 1000);


            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ttype=1;
                        tpage++;
                        gettuiianp.getduanzidata("",1,tpage);

                    }
                }, 1000);
            }
        });
        xlist_guanzhu = view.findViewById(R.id.xlist_guanzhu);
        xlist_guanzhu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xlist_tuijian.setVisibility(View.GONE);
                xlist_guanzhu.setVisibility(View.VISIBLE);
                tv_guanzhu.setTextColor(Color.parseColor("#03A9F4"));
                tv_remen.setTextColor(Color.parseColor("#cccccc"));
                v_guanzhu.setVisibility(View.VISIBLE);
                v_remen.setVisibility(View.GONE);
            }
        });
        rela_remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xlist_tuijian.setVisibility(View.VISIBLE);
                xlist_guanzhu.setVisibility(View.GONE);
                tv_guanzhu.setTextColor(Color.parseColor("#cccccc"));
                tv_remen.setTextColor(Color.parseColor("#03A9F4"));
                v_guanzhu.setVisibility(View.GONE);
                v_remen.setVisibility(View.VISIBLE);
            }
        });
        getadp.getad();

    }



    @Override
    public void ondistory() {

        if(tuijianapter!=null){
            tuijianapter=null;
            }

          if(mBanner!=null){
            mBanner=null;
          }

    }

    @Override
    public List<Basepresent> inip() {
        List<Basepresent> list=new ArrayList<>();
        list.add(getadp);
        list.add(gettuiianp);
        return list;
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView) view);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(mBanner!=null){
            mBanner.startAutoPlay();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBanner!=null) {
            mBanner.stopAutoPlay();
        }
    }
    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }



    @Override
    public void getdatasuess(List<TuijianBean> list) {
        if(list!=null){
            ani.stopLoading();
            ani.setVisibility(View.GONE);
            xlist_tuijian.setVisibility(View.VISIBLE);
            if(tuijianapter==null){
                tuijianapter = new Tuijianapter(list,getActivity());
                xlist_tuijian.setAdapter(tuijianapter);
            }else {
                if(ttype==0){
                    tuijianapter.Refresh(list);
                    xlist_tuijian.refreshComplete();
                }else {
                    tuijianapter.LoadMore(list);
                    xlist_tuijian.loadMoreComplete();
                }
            }

        }else {
            ani.setVisibility(View.VISIBLE);
            xlist_tuijian.setVisibility(View.GONE);
        }


    }

    @Override
    public void getdatafail(String msg) {

    }

    @Override
    public void adsuccess(List<Guanggao> list) {
        if(list!=null){
            if(getActivity()!=null){
                View header = View.inflate(getActivity(), R.layout.xbanner, null);
                mBanner = header.findViewById(R.id.banner);
                imgesUrl = new ArrayList<>();
                List<String>  title=new ArrayList<>();
                for (int i = 0; i <list.size() ; i++) {
                    imgesUrl.add(list.get(i).icon);
                    title.add(list.get(i).title);
                }
                mBanner.setData(imgesUrl,title);
                mBanner.setmAdapter(this);
                xlist_tuijian.addHeaderView(header);
                gettuiianp.getduanzidata("",1,tpage);
            }
        }
    }

    @Override
    public void adfail(String msg) {

    }
}
