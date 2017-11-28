package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.asus.yikezhong.R;

import java.util.ArrayList;
import java.util.List;

import adapter.Duanziapter;
import bean.Duanzibean;
import bean.UserBean;
import mInterface.Duanziview;
import mybase.Basefragment;
import mybase.Basepresent;
import mybase.Baseview;
import present.Getdatap;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Duanzi extends Basefragment implements Duanziview{

    private XRecyclerView recyclerView;
    private Getdatap getdatap;
    private Duanziapter duanziapter;
    private  int type=0;
    private  int page=1;

    @Override
    public int getlayoutid() {
        return R.layout.duanzi;
    }
    @Override
    public void init() {
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                type=0;
                page=1;
                getdatap.getduanzidata(page);
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                type=1;
                page++;
                getdatap.getduanzidata(page);
                recyclerView.loadMoreComplete();
            }
        });
        getdatap = new Getdatap(this);
        getdatap.getduanzidata(page);
    }

    @Override
    public List<Basepresent> inip() {
        List<Basepresent> list=new ArrayList<>();
        list.add(getdatap);
        return list;
    }



    @Override
    public void getdatasuess(List<Duanzibean> list) {
        if(duanziapter==null){
            duanziapter = new Duanziapter(list,getActivity());
            recyclerView.setAdapter(duanziapter);
        }else {
            if(type==0){
                duanziapter.Refresh(list);
            }else {
                duanziapter.LoadMore(list);
            }
        }
    }



    @Override
    public void getdatafail(String msg) {

    }


    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }
}
