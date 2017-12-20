package commpont;

import com.liu.asus.yikezhong.MainActivity;
import com.liu.asus.yikezhong.OtherloginActivity;
import com.liu.asus.yikezhong.ShezhiActivity;

import dagger.Component;
import mymodules.Mainmoudule;
import mymodules.Upvisionmoudule;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */

@Component(modules = {Upvisionmoudule.class})
public interface Upvisioncommpont {
    void   upjects(ShezhiActivity activity);
}
