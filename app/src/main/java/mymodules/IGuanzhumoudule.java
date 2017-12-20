package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.IGuanzhuv;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class IGuanzhumoudule {
    private IGuanzhuv iGuanzhuv;

    public IGuanzhumoudule(IGuanzhuv iGuanzhuv) {
        this.iGuanzhuv = iGuanzhuv;
    }

    @Provides
    IGuanzhuv provideIGuanzhuView() {
        return iGuanzhuv;
    }
}
