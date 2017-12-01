package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
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

public class Duanzi extends Basefragment implements Duanziview, Duanziapter.DuanzicallBack {

    private XRecyclerView recyclerView;
    private Getdatap getdatap;
    private Duanziapter duanziapter;
    private  int type=0;
    private  int page=1;
    private Handler handler=new Handler();

    @Override
    public int getlayoutid() {
        return R.layout.duanzi;
    }
    @Override
    public void init() {
        Fresco.getImagePipeline().clearCaches();
        //清空硬盘缓存，一般是用户手动清理
        Fresco.getImagePipeline().clearDiskCaches();
        //清空内存缓存（包括Bitmap缓存和未解码图片的缓存）
        Fresco.getImagePipeline().clearMemoryCaches();
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRefreshProgressStyle(16);//刷新样式
        recyclerView.setLaodingMoreProgressStyle(16);//加载样式
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        type=0;
                        page=1;
                        getdatap.getduanzidata(page);
                        recyclerView.refreshComplete();
                    }
                }, 1000);

            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        type=1;
                        page++;
                        getdatap.getduanzidata(page);
                        recyclerView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        getdatap = new Getdatap(this);
        getdatap.getduanzidata(page);

    }

    @Override
    public void ondistory() {
        if(handler!=null){
            handler=null;
        }
    }

    @Override
    public List<Basepresent> inip() {
        List<Basepresent> list=new ArrayList<>();
        list.add(getdatap);
        return list;
    }



    @Override
    public void getdatasuess(List<Duanzibean> list) {
        if(list!=null){
            if(duanziapter==null){
                duanziapter = new Duanziapter(list,getActivity());
                duanziapter.setDuanzicallBack(this);
                recyclerView.setAdapter(duanziapter);
            }else {
                if(type==0){
                    duanziapter.Refresh(list);
                }else {
                    duanziapter.LoadMore(list);
                }
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

    @Override
    public void DuanziBack(View view) {
        Toast.makeText(getActivity(), "你好", Toast.LENGTH_SHORT).show();
    }
}
