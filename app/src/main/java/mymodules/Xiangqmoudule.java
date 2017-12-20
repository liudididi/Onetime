package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.Xqview;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Xiangqmoudule {
    private Xqview xqview;
    public Xiangqmoudule(Xqview xqview) {
        this.xqview = xqview;
    }

    @Provides
    Xqview provideXqView() {
        return xqview;
    }

}
