package adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.asus.yikezhong.MainActivity;
import com.liu.asus.yikezhong.R;
import com.liu.asus.yikezhong.UserActivity;
import com.liu.asus.yikezhong.VideoActivity;

import java.util.HashMap;
import java.util.List;


import bean.TuijianBean;
import cn.jzvd.JZVideoPlayerStandard;

import utils.SPUtils;


/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Tuijianapter extends RecyclerView.Adapter{
    private List<TuijianBean> list;
    private Context context;
    private  TuijiancallBack duanzicallBack;


    public Tuijianapter(List<TuijianBean> list, Context context, String username) {
        this.list = list;
        this.context = context;

    }

    public void setTuijiancallBack(TuijiancallBack tuijiancallBack) {
        this.duanzicallBack = tuijiancallBack;
    }

    public Tuijianapter(List<TuijianBean> list, Context context) {
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
        View view = View.inflate(context, R.layout.tujian_item, null);
        Myviewholder myviewholder=new Myviewholder(view);
        myviewholder.duanziz_item_tv_name=view.findViewById(R.id.duanziz_item_tv_name);
        myviewholder.duanzi_item_tv_time=view.findViewById(R.id.duanzi_item_tv_time);
        myviewholder.duanzi_item_iv_bianji=view.findViewById(R.id. duanzi_item_iv_bianji);
        myviewholder.iv_touxiang=view.findViewById(R.id.iv_touxiang);
        myviewholder.line_jia=view.findViewById(R.id.line_jia);
        myviewholder.line_2=view.findViewById(R.id.line_2);
        myviewholder.line_3=view.findViewById(R.id.line_3);
        myviewholder.line_4=view.findViewById(R.id.line_4);
        myviewholder.jicao=view.findViewById(R.id.jiecao);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Myviewholder myviewholder= (Myviewholder) holder;
        String videoUrl = list.get(position).videoUrl;
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        myviewholder.jicao.setUp(replace, JZVideoPlayerStandard.SCREEN_WINDOW_LIST, " ");
        Glide.with(context).load(list.get(position).cover).into(myviewholder.jicao.thumbImageView);
        myviewholder.duanziz_item_tv_name.setText(list.get(position).user.nickname);
        myviewholder.duanzi_item_tv_time.setText(list.get(position).createTime);
        if(list.get(position).user.icon!=null){
            myviewholder.iv_touxiang.setImageURI(Uri.parse(list.get(position).user.icon));
        }else {
            myviewholder.iv_touxiang.setImageURI(Uri.parse("res://"+context.getPackageName()+"/" + R.drawable.raw_1499936862));
        }

     //评论
        myviewholder.img_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VideoActivity.class);

                intent.putExtra("wid",list.get(position).wid);
                context.startActivity(intent);
            }
        });
        myviewholder.iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("uid",list.get(position).uid);
                bundle.putString("icon",list.get(position).user.icon);
                bundle.putString("username",list.get(position).user.nickname);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
         final ObjectAnimator   animator = ObjectAnimator.ofFloat( myviewholder.duanzi_item_iv_bianji, "rotation", 0f, 180f);;
         final ObjectAnimator animator1= ObjectAnimator.ofFloat(myviewholder.line_2, "translationX", 0f,-100f);;
         final ObjectAnimator animator2= ObjectAnimator.ofFloat(myviewholder.line_3, "translationX", 0f,-200f);;
         final ObjectAnimator animator3= ObjectAnimator.ofFloat(myviewholder.line_4, "translationX", 0f,-300f);;
         final ObjectAnimator fanimator= ObjectAnimator.ofFloat( myviewholder.duanzi_item_iv_bianji, "rotation", 0f, -180f);;
         final ObjectAnimator fanimator1= ObjectAnimator.ofFloat(myviewholder.line_2, "translationX",  -100f,0f);;
         final ObjectAnimator fanimator2= ObjectAnimator.ofFloat(myviewholder.line_3, "translationX", -200f,0f);;
         final ObjectAnimator fanimator3= ObjectAnimator.ofFloat(myviewholder.line_4, "translationX", -300f,0f);;
          myviewholder.line_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myviewholder.a++;
                if(myviewholder.a%2==1){//第一次点击是实现伸出效果
                    myviewholder.line_2.setVisibility(View.VISIBLE);
                    myviewholder.line_3.setVisibility(View.VISIBLE);
                    myviewholder.line_4.setVisibility(View.VISIBLE);
                    final AnimatorSet animSet = new AnimatorSet();//动画集合
                    animSet.play(animator).with(animator1).with(animator2).with(animator3);
                    animSet.setDuration(500);
                    animSet.start();
                    animSet.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                            myviewholder.duanzi_item_iv_bianji.setImageResource(R.drawable.icon_jian);

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }else{//再点击一次实现缩回效果
                    final AnimatorSet animSet1 = new AnimatorSet();//动画集合
                    animSet1.play(fanimator).with(fanimator1).with(fanimator2).with(fanimator3);
                    animSet1.setDuration(500);
                    animSet1.start();
                    animSet1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            myviewholder.duanzi_item_iv_bianji.setImageResource(R.drawable.icon_jia);
                            myviewholder.line_2.setVisibility(View.GONE);
                            myviewholder.line_3.setVisibility(View.GONE);
                            myviewholder.line_4.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });

                    }public
     static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class Myviewholder extends  RecyclerView.ViewHolder {
         private TextView duanziz_item_tv_name;
         private  TextView   duanzi_item_tv_time;
         private SimpleDraweeView iv_touxiang;
         private ImageView duanzi_item_iv_bianji;
         private LinearLayout line_jia;
         private LinearLayout line_2;
         private LinearLayout line_3;
         private LinearLayout line_4;
         private  int a=0;
         private JZVideoPlayerStandard jicao;
         private  View view;
         private  ImageView img_pinglun;
        public Myviewholder(View itemView) {
            super(itemView);
            this.view=itemView;
            img_pinglun=itemView.findViewById(R.id.img_pinglun);
        }
    }
    public  interface  TuijiancallBack{
        void TuijianBack(View view);
    }
}
