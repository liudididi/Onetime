package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.asus.yikezhong.R;

import java.util.List;

import mybase.Basefragment;
import mybase.Basepresent;

/**
 * Created by 地地 on 2017/11/24.
 * 邮箱：461211527@qq.com.
 */

public class Tuijian  extends Basefragment{


    private RelativeLayout rela_remen,rela_guanzhu;
    private TextView tv_remen;
    private TextView tv_guanzhu;
    private View v_guanzhu;
    private View v_remen;

    @Override
    public int getlayoutid() {
        return R.layout.tuijian;
    }

    @Override
    public void init() {
        tv_remen = view.findViewById(R.id.tv_remen);
        tv_guanzhu = view.findViewById(R.id.tv_guanzhu);
        v_guanzhu = view.findViewById(R.id.v_guanzhu);
        v_remen = view.findViewById(R.id.v_remen);
        rela_remen = view.findViewById(R.id.rela_remen);
        rela_guanzhu = view.findViewById(R.id.rela_guanzhu);
        rela_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_guanzhu.setTextColor(Color.parseColor("#03A9F4"));
                tv_remen.setTextColor(Color.parseColor("#cccccc"));
                v_guanzhu.setVisibility(View.VISIBLE);
                v_remen.setVisibility(View.GONE);
            }
        });
        rela_remen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_guanzhu.setTextColor(Color.parseColor("#cccccc"));
                tv_remen.setTextColor(Color.parseColor("#03A9F4"));
                v_guanzhu.setVisibility(View.GONE);
                v_remen.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public List<Basepresent> inip() {
        return null;
    }
}
