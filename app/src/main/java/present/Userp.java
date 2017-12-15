package present;

import java.util.List;

import javax.inject.Inject;

import bean.TuijianBean;
import m.Getdatamodle;
import mInterface.Userv;

/**
 * Created by 地地 on 2017/12/13.
 * 邮箱：461211527@qq.com.
 */

public class Userp {
    @Inject
    Getdatamodle getdatamodle;
    private Userv userv;
     @Inject
    public Userp(Userv userv) {
         this.userv = userv;
    }


    public void  getdatauser(int uid,int page){

        getdatamodle.getuserdata(uid, page, new Getdatamodle.GetuserdataBack() {
            @Override
            public void success(List<TuijianBean> list) {
                userv.getdatasuess(list);
            }

            @Override
            public void fail(Throwable e) {
                 userv.getdatafail(e.toString());
            }
        });
    }


}
