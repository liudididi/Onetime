package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.Lognview;
import mInterface.Userv;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Usermoudule {
    private Userv userv;
    public Usermoudule(Userv userv) {
        this.userv = userv;
    }

    @Provides
    Userv provideUserView() {
        return userv;
    }

}
