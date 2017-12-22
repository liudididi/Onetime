package com.liu.asus.yikezhong;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mybase.BaseActivity;
import mybase.Basepresent;
import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends BaseActivity {


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pp_num)
    TextView ppNum;
    private ArrayList<String> list;
    private  int numsize;
    private  int currnum;


    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_photo_view;
    }

    @Override
    public void init() {
        list = getIntent().getStringArrayListExtra("list");
        numsize=list.size();
        ppNum.setText("1/"+numsize);
        MyViewPageradapter viewPageradapter = new MyViewPageradapter();
        vp.setAdapter(viewPageradapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ppNum.setText((vp.getCurrentItem()+1)+"/"+numsize);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void ondestory() {

    }

    class MyViewPageradapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View inflate = View.inflate(PhotoViewActivity.this, R.layout.photoitem, null);
            PhotoView photoView = inflate.findViewById(R.id.photo);
            Glide.with(PhotoViewActivity.this).load(list.get(position)).into(photoView);
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (View) object;
        }
    }

}

