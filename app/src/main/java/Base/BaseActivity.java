package Base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getid());
        init();

    }

    public   abstract  List<Basepresent> initp() ;
    public  abstract int getid();
    public  abstract  void init();
    public  void  Toast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        List<Basepresent> initp = initp();
        for (Basepresent basepresent : initp()) {
            basepresent.ondeach();
        }
        super.onDestroy();
    }
}
