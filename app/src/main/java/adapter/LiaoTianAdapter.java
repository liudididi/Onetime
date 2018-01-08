package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liu.asus.yikezhong.R;
import com.meg7.widget.CircleImageView;

import java.util.List;

import bean.LiaoTianBean;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/12/22.
 * 邮箱：461211527@qq.com.
 */

public class LiaoTianAdapter extends BaseAdapter {
    private Context context;
    private List<LiaoTianBean> list;
    private  String dicon;


    private  String myicon;

    private  int a=0;
    private  int b=1;

    public LiaoTianAdapter(Context context, List<LiaoTianBean> list, String dicon) {
        this.context = context;
        this.list = list;
        this.dicon = dicon;
        myicon= (String) SPUtils.get(context,"icon","");

    }
    public void Refresh(LiaoTianBean liaoTianBean) {
        if(list!=null){
            list.add(liaoTianBean);
            this.notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).type==a){
            return a;
        }else {
            return b;
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        Myviewhoder holder=null;
        if(view==null){
         holder = new Myviewhoder();
        switch (itemViewType){
            case  0:
                view=View.inflate(context, R.layout.liaotianitem_a,null);
                holder.lt_icon=view.findViewById(R.id.lt_icon);
                holder.lt_neirong=view.findViewById(R.id.lt_count);
                holder.tv_time=view.findViewById(R.id.tv_time);
                 view.setTag(holder);

                break;
               case  1:
                   view=View.inflate(context, R.layout.liaotianitem_b,null);
                   holder.lt_icon=view.findViewById(R.id.lt_icon);
                   holder.lt_neirong=view.findViewById(R.id.lt_count);
                   holder.tv_time=view.findViewById(R.id.tv_time);
                   view.setTag(holder);
                break;
            }
        }else {
            holder = (Myviewhoder) view.getTag();
        }

        switch (itemViewType){
            case 0:   //text message
                holder.lt_neirong.setText(list.get(i).text);
                Glide.with(context).load(myicon).into(holder.lt_icon);
                holder.tv_time.setText(list.get(i).time);
                break;
            case 1:   //sound message
                holder.lt_neirong.setText(list.get(i).text);
                Glide.with(context).load(dicon).into(holder.lt_icon);
                holder.tv_time.setText(list.get(i).time);
                break;
        }

        return view;
    }

    class  Myviewhoder{
        public TextView lt_neirong;
        public TextView tv_time;
        public CircleImageView lt_icon;
    }
}
