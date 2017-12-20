package present;

import javax.inject.Inject;

import bean.TuijianBean;
import fragment.Tuijian;
import m.Xiangqm;
import mInterface.Xqview;
import mybase.Basebean;

/**
 * Created by 地地 on 2017/12/18.
 * 邮箱：461211527@qq.com.
 */

public class Xqpresent {
    @Inject
    Xiangqm xiangqm;
    private Xqview xqview;
    @Inject
    public Xqpresent(Xqview xqview) {
        this.xqview = xqview;
    }


    public void  GetXiangqing(final int wid){
        xiangqm.getXiangqing(wid, new Xiangqm.XqRequestBack() {
            @Override
            public void getdatasuccess(Basebean<TuijianBean> Basebean) {

                if(Basebean.code.equals("0")){
                    xqview.getdatasuccess(Basebean.data);
                }else {
                    xqview.getdatafainl(Basebean.msg);
                }

            }
            @Override
            public void fail(Throwable t) {
                xqview.getdatafainl(t.toString());
            }
        });


    }
}
