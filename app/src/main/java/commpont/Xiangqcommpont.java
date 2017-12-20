package commpont;

import com.liu.asus.yikezhong.IGuanzhuActivity;
import com.liu.asus.yikezhong.VideoActivity;

import dagger.Component;
import mymodules.IGuanzhumoudule;
import mymodules.Xiangqmoudule;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */

@Component(modules = {Xiangqmoudule.class})
public interface Xiangqcommpont {
    void  injectXq(VideoActivity activity);
}
