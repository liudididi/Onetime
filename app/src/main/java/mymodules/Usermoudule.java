package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.Lognview;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Usermoudule {
    private  Lognview lognview;
    public Usermoudule(Lognview lognview) {
        this.lognview = lognview;
    }

    @Provides
    Lognview provideMainView() {
        return lognview;
    }

}
