package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liu.asus.yikezhong.R;
import com.liu.asus.yikezhong.UserActivity;

import java.util.List;

import bean.GuanzhuBean;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class SousuoAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<GuanzhuBean> list;

    public SousuoAdapter(Context context, List<GuanzhuBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.sousuoitem, null);
        Myigzviewhodler myigzviewhodler=new Myigzviewhodler(inflate);
        return myigzviewhodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Myigzviewhodler myigzviewhodler= (Myigzviewhodler) holder;
        myigzviewhodler.igz_tv_name.setText(list.get(position).nickname);
       String icon=list.get(position).icon;
        if(icon!=null&&icon.length()>=3){
            Glide.with(context).load(list.get(position).icon).into(myigzviewhodler.igz_icon);
        }
        myigzviewhodler.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("uid",list.get(position).uid);
                bundle.putString("icon",list.get(position).icon);
                bundle.putString("username",list.get(position).nickname);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class Myigzviewhodler extends RecyclerView.ViewHolder{
         private ImageView igz_icon;
         private TextView igz_tv_name;
         private View view;

        public Myigzviewhodler(View itemView) {
            super(itemView);
            view=itemView;
            igz_icon=itemView.findViewById(R.id.igz_ce_icon);
            igz_tv_name=itemView.findViewById(R.id.igz_tv_name);
        }
    }

}
