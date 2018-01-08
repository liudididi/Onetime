package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.liu.asus.yikezhong.IGuanzhuActivity;
import com.liu.asus.yikezhong.LiaoTianActivity;
import com.liu.asus.yikezhong.R;

import java.util.List;

import bean.GuanzhuBean;
import utils.DragBallView;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class IguanzhuAdapter  extends RecyclerView.Adapter{
    private Context context;
    private List<GuanzhuBean> list;

    public IguanzhuAdapter(Context context, List<GuanzhuBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.igzitem, null);
        Myigzviewhodler myigzviewhodler=new Myigzviewhodler(inflate);
        return myigzviewhodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Myigzviewhodler myigzviewhodler= (Myigzviewhodler) holder;

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation("15297526557");
        int unreadMsgCount = conversation.getUnreadMsgCount();
        if(unreadMsgCount!=0&&list.get(position).mobile.equals("15297526557")){

        }else {

        }
        myigzviewhodler.igz_tv_name.setText(list.get(position).nickname);
        myigzviewhodler.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LiaoTianActivity.class);
                intent.putExtra("mobile",list.get(position).mobile);
                intent.putExtra("dname",list.get(position).nickname);
                intent.putExtra("dicon",list.get(position).icon);
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(list.get(position).icon).into(myigzviewhodler.igz_icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Myigzviewhodler extends RecyclerView.ViewHolder{
         private ImageView igz_icon;
         private TextView igz_tv_name;
         private  View view;
          public Myigzviewhodler(View itemView) {
            super(itemView);
            igz_icon=itemView.findViewById(R.id.igz_ce_icon);
            igz_tv_name=itemView.findViewById(R.id.igz_tv_name);
            view=itemView;
        }
    }



}
