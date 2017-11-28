package fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.asus.yikezhong.R;

import java.util.List;

import mybase.Basefragment;
import mybase.Basepresent;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Celeft extends Basefragment{


    private SimpleDraweeView ce_icon;

    @Override
    public int getlayoutid() {
        return R.layout.celeft;
    }

    @Override
    public void init() {
        ce_icon = view.findViewById(R.id.ce_icon);
        String icon = (String) SPUtils.get(getActivity(), "icon", "");
        int uid = (int) SPUtils.get(getActivity(), "uid", 0);
        if(uid!=0){
        if (icon != null && icon.length() >= 3) {
            ce_icon.setImageURI(Uri.parse(icon));
        }else {
            ce_icon.setImageURI(Uri.parse("res://"+getActivity().getPackageName()+"/" + R.drawable.raw_1499936862));
            }
        }
    }

    @Override
    public List<Basepresent> inip() {
        return null;
    }
}
