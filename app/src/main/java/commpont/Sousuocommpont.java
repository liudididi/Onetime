package commpont;

import com.liu.asus.yikezhong.IGuanzhuActivity;
import com.liu.asus.yikezhong.SousuoActivity;

import dagger.Component;
import mymodules.IGuanzhumoudule;
import mymodules.Sousuomoudule;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */

@Component(modules = {Sousuomoudule.class})
public interface Sousuocommpont {
    void  injectSousuo(SousuoActivity activity);
}
