package commpont;

import com.liu.asus.yikezhong.IGuanzhuActivity;
import com.liu.asus.yikezhong.MainActivity;
import com.liu.asus.yikezhong.OtherloginActivity;

import dagger.Component;
import mymodules.IGuanzhumoudule;
import mymodules.Mainmoudule;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */

@Component(modules = {IGuanzhumoudule.class})
public interface Iguanzhucommpont {
    void  injectIguan(IGuanzhuActivity activity);
}
