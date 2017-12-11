package present;

import java.util.List;

import bean.Duanzibean;
import bean.TuijianBean;
import fragment.Tuijian;
import m.Getdatamodle;
import mInterface.Duanziview;
import mInterface.Tuijianview;
import mybase.Basepresent;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Gettuiianp extends Basepresent {
    private Getdatamodle getdatamodle;
    private Tuijianview tujianview;
    public Gettuiianp(Object viewmodel) {
        super(viewmodel);
        this.tujianview= (Tuijianview) viewmodel;
        if(getdatamodle==null){
            getdatamodle=new Getdatamodle();
        }
    }
    public  void  getduanzidata(String uid, int type, int page){
        getdatamodle.gettuijian(uid, type, page, new Getdatamodle.requesttuijianBack() {
            @Override
            public void success(List<TuijianBean> list) {

                tujianview.getdatasuess(list);
            }
            @Override
            public void fail(Throwable e) {

                tujianview.getdatafail(e.toString());
            }
        });
       }
}
