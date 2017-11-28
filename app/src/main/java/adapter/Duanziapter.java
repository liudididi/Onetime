package adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.asus.yikezhong.R;

import java.util.List;

import bean.Duanzibean;
import bean.UserBean;

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
    public void Refresh(List<Duanzibean> newlist) {
        if(list!=null){
            list.clear();
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }

    }
    public void LoadMore(List<Duanzibean> newlist) {
        if(list!=null){
            list.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.duanzi_item, null);
        Myviewholder myviewholder=new Myviewholder(view);
        myviewholder.duanziz_item_tv_name=view.findViewById(R.id.duanziz_item_tv_name);
        myviewholder.duanzi_tv_content=view.findViewById(R.id.duanzi_tv_content);
        myviewholder.duanzi_item_tv_time=view.findViewById(R.id.duanzi_item_tv_time);
        myviewholder.duanzi_item_iv_bianji=view.findViewById(R.id. duanzi_item_iv_bianji);

        myviewholder.iv_touxiang=view.findViewById(R.id.iv_touxiang);

        myviewholder.line_jia=view.findViewById(R.id.line_jia);
        myviewholder.line_2=view.findViewById(R.id.line_2);
        myviewholder.line_3=view.findViewById(R.id.line_3);
        myviewholder.line_4=view.findViewById(R.id.line_4);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Myviewholder myviewholder= (Myviewholder) holder;
        myviewholder.duanziz_item_tv_name.setText(list.get(position).user.nickname);
        myviewholder.duanzi_tv_content.setText(list.get(position).content);
        myviewholder.duanzi_item_tv_time.setText(list.get(position).createTime);
        myviewholder.iv_touxiang.setImageURI(Uri.parse(list.get(position).user.icon));
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
                    AnimatorSet animSet = new AnimatorSet();//动画集合
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
                    AnimatorSet animSet1 = new AnimatorSet();//动画集合
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class Myviewholder extends  RecyclerView.ViewHolder {
         private TextView duanziz_item_tv_name;
         private  TextView   duanzi_item_tv_time;
         private SimpleDraweeView iv_touxiang;
         private  TextView duanzi_tv_content;
         private ImageView duanzi_item_iv_bianji;
         private LinearLayout line_jia;
         private LinearLayout line_2;
         private LinearLayout line_3;
         private LinearLayout line_4;
         private  int a=0;


        public Myviewholder(View itemView) {
            super(itemView);
        }
    }
}
