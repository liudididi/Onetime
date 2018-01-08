package mybase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.liu.asus.yikezhong.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public abstract  class BaseActivity extends AutoLayoutActivity{
    private  boolean isStatus=false;
    private  boolean  isFullScreen=false;
    private Toast toast;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppT);
        super.onCreate(savedInstanceState);

// 状态栏颜色
        initztl("#03A9F4");
        setContentView(getid());
        ButterKnife.bind(this);
        init();

    }

    public   abstract  List<Basepresent> initp() ;
    public  abstract int getid();
    public  abstract  void init();
    public  abstract  void ondestory();

    /**
     * 设置状态栏
     * @param color
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void initztl(String color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
    /**
     * 设置透明状态：沉浸式
     * @param status
     */
    public void setStatus(boolean status) {
        isStatus = status;
        if (isStatus){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
    /**
     * 设置全屏
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        if (isFullScreen){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    public  void  Toast(String s){
        if(toast==null){
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
            toast.show();
        }else {
            toast.show();
        }

    }
   public   void  intent(Context packageContext, Class<?> cls){
         Intent intent=new Intent(packageContext,cls);
         startActivity(intent);

   }
    @Override
    protected void onDestroy() {
        ondestory();
        List<Basepresent> initp = initp();
        if(initp!=null){
            for (Basepresent basepresent : initp()) {
                basepresent.ondeach();
        }
        initp=null;
        }
        super.onDestroy();
    }
}
