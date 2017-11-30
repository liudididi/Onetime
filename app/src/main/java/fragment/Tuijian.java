package fragment;

import android.graphics.Color;
import android.os.Bundle;
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
import mInterface.Duanziview;
import mybase.Basefragment;
import mybase.Basepresent;
import present.Getdatap;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Tuijian  extends Basefragment implements XBanner.XBannerAdapter, Duanziview {


    private RelativeLayout rela_remen,rela_guanzhu;
    private TextView tv_remen;
    private TextView tv_guanzhu;
    private View v_guanzhu;
    private View v_remen;
    private XRecyclerView xlist_tuijian,xlist_guanzhu;
    private List<String> imgesUrl;
    private Getdatap getdatap;

    @Override
    public int getlayoutid() {
        return R.layout.tuijian;
    }

    @Override
    public void init() {
        getdatap=new Getdatap(this);




        tv_remen = view.findViewById(R.id.tv_remen);
        tv_guanzhu = view.findViewById(R.id.tv_guanzhu);
        v_guanzhu = view.findViewById(R.id.v_guanzhu);
        v_remen = view.findViewById(R.id.v_remen);
        rela_remen = view.findViewById(R.id.rela_remen);
        rela_guanzhu = view.findViewById(R.id.rela_guanzhu);
        xlist_tuijian = view.findViewById(R.id.xlist_tuijian);
        xlist_tuijian.setLayoutManager(new LinearLayoutManager(getActivity()));
        initheader();
        xlist_guanzhu = view.findViewById(R.id.xlist_guanzhu);
        xlist_guanzhu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_guanzhu.setTextColor(Color.parseColor("#03A9F4"));
                tv_remen.setTextColor(Color.parseColor("#cccccc"));
                v_guanzhu.setVisibility(View.VISIBLE);
                v_remen.setVisibility(View.GONE);
            }
        });
        rela_remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_guanzhu.setTextColor(Color.parseColor("#cccccc"));
                tv_remen.setTextColor(Color.parseColor("#03A9F4"));
                v_guanzhu.setVisibility(View.GONE);
                v_remen.setVisibility(View.VISIBLE);
            }
        });
        getdatap.getduanzidata(1);
    }

    private void initheader() {
        View header = View.inflate(getActivity(), R.layout.xbanner, null);
        XBanner mBanner = header.findViewById(R.id.banner);
        imgesUrl = new ArrayList<>();
        imgesUrl.add("http://pic1.cxtuku.com/00/06/78/b9903ad9ea2b.jpg");
        imgesUrl.add("http://pic1.cxtuku.com/00/06/78/b9903ad9ea2b.jpg");
        imgesUrl.add("http://pic1.cxtuku.com/00/06/78/b9903ad9ea2b.jpg");
        imgesUrl.add("http://pic1.cxtuku.com/00/06/78/b9903ad9ea2b.jpg");
        mBanner.setData(imgesUrl,null);
        mBanner.setmAdapter(this);
        xlist_tuijian.addHeaderView(header);

    }

    @Override
    public void ondistory() {

    }



    @Override
    public List<Basepresent> inip() {
        return null;
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView) view);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void getdatasuess(List<Duanzibean> list) {
       // Tuijianapter tuijianapter=new Tuijianapter(list,getActivity());
        //xlist_tuijian.setAdapter(tuijianapter);

    }

    @Override
    public void getdatafail(String msg) {

    }
}
