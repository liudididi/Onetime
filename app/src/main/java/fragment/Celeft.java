package fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.asus.yikezhong.LoginActivity;
import com.liu.asus.yikezhong.MainActivity;
import com.liu.asus.yikezhong.R;
import com.meg7.widget.CircleImageView;
import com.meg7.widget.CustomShapeImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mybase.Basefragment;
import mybase.Basepresent;
import mybase.Baseview;
import present.Changepresent;
import present.Nichengp;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Celeft extends Basefragment implements Baseview{


    private CircleImageView ce_icon;
    private TextView tv_name,tv_zhuxiao;
    private int uid;
    private  Ce_iconback ce_iconback;
    private ImageView img_xiugai;
    private PopupWindow mPopWindow;
    private TextView pop_finish;
    private EditText pop_ed;
    private TextView pop_queding;
    private Nichengp nichengp;


    public void setCe_iconback(Ce_iconback ce_iconback) {
        this.ce_iconback = ce_iconback;
    }

    @Override
    public int getlayoutid() {
        return R.layout.celeft;
    }

    @Override
    public void init() {
        View contentView = View.inflate(getActivity(), R.layout.popw_nicheng, null);
        nichengp = new Nichengp(this);
        mPopWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pop_ed = contentView.findViewById(R.id.pop_ed);
        pop_queding = contentView.findViewById(R.id.pop_queding);
        pop_finish = contentView.findViewById(R.id.pop_finish);
        pop_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        pop_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(TextUtils.isEmpty(pop_ed.getText().toString())){
                  Toast.makeText(getActivity(), "昵称不能为空", Toast.LENGTH_SHORT).show();
              }else {
                  nichengp.xiugai(uid,pop_ed.getText().toString());
              }
            }
        });
        ce_icon = view.findViewById(R.id.ce_icon);
        tv_name = view.findViewById(R.id.tv_name);
        tv_zhuxiao = view.findViewById(R.id.tv_zhuxiao);
        img_xiugai= view.findViewById(R.id.img_xiugai);
        tv_zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.clear(getActivity());
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        img_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.celeft, null);
                mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
            }
        });
        ce_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("设置头像");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("修改头像", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ce_iconback.Changge(ce_icon);
                    }
                });
                builder.create().show();
            }
        });

    }


    public void ondistory() {


    }

    @Override
    public List<Basepresent> inip() {
        List<Basepresent> list=new ArrayList<>();
        list.add(nichengp);
        return list;
    }

    @Override
    public void onResume() {
        uid = (int) SPUtils.get(getActivity(), "uid", 0);
       String name= (String) SPUtils.get(getActivity(), "nickname", "");
       if(name!=null){
           if(name.length()>=6){
               String substring = name.substring(0, 6);
               tv_name.setText(substring+"...");
           }else {
               tv_name.setText(name);
           }

           pop_ed.setText(name);
       }
        if(uid !=0){
            String icon = (String) SPUtils.get(getActivity(), "icon", "");
            if (icon != null && icon.length() >= 3) {
                Glide.with(getActivity()).load(icon)
                       .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .dontAnimate()
                        .into(ce_icon);
            }else {
                Glide.with(this).load(R.drawable.raw_1499936862)
                        .into(ce_icon);
            }
        }else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(ce_icon);
        }
        super.onResume();
    }

    @Override
    public void success() {
        Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
        String name = pop_ed.getText().toString();
        SPUtils.put(getActivity(),"nickname",name);
        if(name.length()>=6){
            String substring = name.substring(0, 6);
            tv_name.setText(substring+"...");
        }else {
            tv_name.setText(name);
        }
        mPopWindow.dismiss();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public  interface  Ce_iconback {
        void  Changge(CircleImageView icon);
    }

}
