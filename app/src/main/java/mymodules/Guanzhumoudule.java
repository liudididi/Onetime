package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.Lognview;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Guanzhumoudule {
    private Baseview baseview;
    public Guanzhumoudule(Baseview baseview) {
        this.baseview = baseview;
    }

    @Provides
    Baseview provideMainView() {
        return baseview;
    }

}
