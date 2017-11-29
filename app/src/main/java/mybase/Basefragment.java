package mybase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 地地 on 2017/11/25.
 * 邮箱：461211527@qq.com.
 */

public abstract  class Basefragment extends Fragment {

    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view= View.inflate(getContext(),getlayoutid(),null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

    }
    public  abstract int getlayoutid();
    public  abstract void  init();
    public  abstract void  ondistory();
    public  abstract List<Basepresent> inip();

    @Override
    public void onDestroy() {
        ondistory();
        List<Basepresent> inip = inip();
        if(inip!=null){
            for (Basepresent basepresent : inip) {
                basepresent.ondeach();
            }
            inip=null;
        }
        super.onDestroy();
    }
}
