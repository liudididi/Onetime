package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.UpVisonv;
import mInterface.Userv;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Upvisionmoudule {
    private UpVisonv upVisonv;
    public Upvisionmoudule(UpVisonv upVisonv) {
        this.upVisonv = upVisonv;
    }
    @Provides
    UpVisonv provideUpVisonv() {
        return upVisonv;
    }
}
