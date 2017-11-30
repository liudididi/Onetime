package fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.asus.yikezhong.R;
import com.meg7.widget.CircleImageView;
import com.meg7.widget.CustomShapeImageView;

import java.util.List;

import mybase.Basefragment;
import mybase.Basepresent;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Celeft extends Basefragment{


    private CircleImageView ce_icon;

    @Override
    public int getlayoutid() {
        return R.layout.celeft;
    }

    @Override
    public void init() {
        ce_icon = view.findViewById(R.id.ce_icon);
    }

    @Override
    public void ondistory() {

    }

    @Override
    public List<Basepresent> inip() {
        return null;
    }

    @Override
    public void onResume() {
        int uid = (int) SPUtils.get(getActivity(), "uid", 0);
        if(uid!=0){
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
}
