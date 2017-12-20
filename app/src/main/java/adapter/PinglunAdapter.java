package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.asus.yikezhong.R;

import java.util.List;

import bean.TuijianBean;

/**
 * Created by 地地 on 2017/12/18.
 * 邮箱：461211527@qq.com.
 */

public class PinglunAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TuijianBean.Pinglun> list;

    public PinglunAdapter(Context context, List<TuijianBean.Pinglun> list) {
        this.context = context;
        this.list = list;
        System.out.println("pinglun"+list.size());
    }


    public void Refresh(List<TuijianBean.Pinglun> newlist) {
        if(list!=null){
            list.clear();
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.pinglunitem, null);
        Mypinglunholder mypinglunholder=new Mypinglunholder(inflate);
        return mypinglunholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mypinglunholder mypinglunholder= (Mypinglunholder) holder;

        mypinglunholder.tv_count.setText(list.get(position).content);
        mypinglunholder.tv_nikename.setText(list.get(position).nickname);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  class  Mypinglunholder extends RecyclerView.ViewHolder{
        private TextView tv_nikename;
        private TextView tv_count;
        public Mypinglunholder(View itemView) {
            super(itemView);
            tv_nikename=itemView.findViewById(R.id.pl_nikename);
            tv_count=itemView.findViewById(R.id.pl_count);
        }
    }
}
