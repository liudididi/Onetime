package mymodules;

import dagger.Module;
import dagger.Provides;
import mInterface.IGuanzhuv;
import mInterface.Sousuov;

/**
 * Created by 地地 on 2017/12/11.
 * 邮箱：461211527@qq.com.
 */
@Module
public class Sousuomoudule {
    private Sousuov sousuov;

    public Sousuomoudule(Sousuov sousuov) {
        this.sousuov = sousuov;
    }

    @Provides
    Sousuov provideSousuoView()
    {
        return sousuov;
    }
}
