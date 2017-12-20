package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.liu.asus.yikezhong.R;
import com.meg7.widget.CircleImageView;
import com.meg7.widget.CustomShapeImageView;

import java.util.List;

import bean.TuijianBean;

/**
 * Created by 地地 on 2017/12/7.
 * 邮箱：461211527@qq.com.
 */

public class SpHotAdapter extends  RecyclerView.Adapter {

    private List<TuijianBean> list;
    private Context context;
    private  SpHotBlack spHotBlack;

    public void setSpHotBlack(SpHotBlack spHotBlack) {
        this.spHotBlack = spHotBlack;
    }

    public SpHotAdapter(List<TuijianBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void Refresh(List<TuijianBean> newlist) {
        if(list!=null){
            list.clear();
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }
    public void LoadMore(List<TuijianBean> newlist) {
        if(list!=null){
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.sp_hot, null);
        MyHotviewhoder myHotviewhoder=new MyHotviewhoder(inflate);
        return myHotviewhoder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHotviewhoder  myHotviewhoder= (MyHotviewhoder) holder;
        myHotviewhoder.setIsRecyclable(false);
        RelativeLayout.LayoutParams para;
        para = (RelativeLayout.LayoutParams) myHotviewhoder.imageView.getLayoutParams();
        para.height= list.get(position).hight;
        myHotviewhoder.imageView.setLayoutParams(para);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.wait);
        options.error(R.drawable.wait);
        myHotviewhoder.imageView.setScaleType(ImageView.ScaleType.FIT_XY) ;
        Glide.with(context).load(list.get(position).cover).apply(options).into(myHotviewhoder.imageView);
        myHotviewhoder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spHotBlack.getvideourl(list.get(position).wid);
            }
        });

        if(list.get(position).user.icon!=null&&list.get(position).user.icon.length()>3){

            Glide.with(context).load(list.get(position).user.icon).into(myHotviewhoder.sp_icon);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  class  MyHotviewhoder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private CircleImageView sp_icon;
        public MyHotviewhoder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.sp_img);
            sp_icon=itemView.findViewById(R.id.sp_icon);
        }
    }

    public  interface  SpHotBlack{
        void  getvideourl(int wid);
    }

}
