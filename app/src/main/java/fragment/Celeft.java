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

import mybase.Basefragment;

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
        ce_icon.setImageURI(Uri.parse("res://"+getActivity().getPackageName()+"/" + R.drawable.raw_1499936862));

    }
}
