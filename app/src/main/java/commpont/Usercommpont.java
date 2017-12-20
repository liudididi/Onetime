package commpont;

import android.app.Activity;

import com.liu.asus.yikezhong.UserActivity;

import dagger.Component;
import mymodules.Guanzhumoudule;
import mymodules.Usermoudule;

/**
 * Created by 地地 on 2017/12/13.
 * 邮箱：461211527@qq.com.
 */
@Component(modules = {Usermoudule.class, Guanzhumoudule.class})
public interface Usercommpont {
    void injectu(UserActivity userActivity);
}
