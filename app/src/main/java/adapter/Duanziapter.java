package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.asus.yikezhong.R;

import java.util.List;

import bean.Duanzibean;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Duanziapter  extends RecyclerView.Adapter{
    private List<Duanzibean> list;
    private Context context;

    public Duanziapter(List<Duanzibean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.duanzi_item, null);
        Myviewholder myviewholder=new Myviewholder(view);
        myviewholder.duanziz_item_tv_name=view.findViewById(R.id.duanziz_item_tv_name);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Myviewholder myviewholder= (Myviewholder) holder;
        myviewholder.duanziz_item_tv_name.setText(list.get(position).content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class Myviewholder extends  RecyclerView.ViewHolder {
         private TextView duanziz_item_tv_name;

        public Myviewholder(View itemView) {
            super(itemView);
        }
    }
}
