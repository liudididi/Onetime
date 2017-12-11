package commpont;

import com.liu.asus.yikezhong.MainActivity;
import com.liu.asus.yikezhong.UserActivity;

import dagger.Component;
import mymodules.Mainmoudule;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */

@Component(modules = {Mainmoudule.class})
public interface Maicommpont {
    void  inject(MainActivity mainActivity);
    void  injecta(UserActivity userActivity);
}
