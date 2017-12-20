package present;

import java.util.List;

import javax.inject.Inject;

import bean.GuanzhuBean;
import m.IGuanzhumodle;
import mInterface.IGuanzhuv;
import mybase.Basebean;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class IGuanzhup {
    @Inject
    IGuanzhumodle iGuanzhumodle;
    private IGuanzhuv iGuanzhuv;
    @Inject
    public IGuanzhup(IGuanzhuv iGuanzhuv) {
        this.iGuanzhuv = iGuanzhuv;
    }
    public void  getIguanzhudata(int uid){
        iGuanzhumodle.getIguanzhudata(uid, new IGuanzhumodle.RequestBack() {
            @Override
            public void getdatasuccess(Basebean<List<GuanzhuBean>> listBasebean) {



                iGuanzhuv.getiguanzhusuccess(listBasebean.data);
            }

            @Override
            public void fail(Throwable t) {
                iGuanzhuv.getiguanzhufail(t.toString());
            }
        });
    }



}
