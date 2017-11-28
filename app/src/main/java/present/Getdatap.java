package present;

import java.util.List;

import bean.Duanzibean;
import bean.UserBean;
import m.Getdatamodle;
import mInterface.Duanziview;
import mybase.Basepresent;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Getdatap extends Basepresent {
    private Getdatamodle getdatamodle;
    private Duanziview duanziview;

    public Getdatap(Object viewmodel) {
        super(viewmodel);
        this.duanziview= (Duanziview) viewmodel;
        if(getdatamodle==null){
            getdatamodle=new Getdatamodle();
        }
    }
    public  void  getduanzidata(int page){
        getdatamodle.getduanzidata(page, new Getdatamodle.requestBack() {
            @Override
            public void success(List<Duanzibean> list) {
                duanziview.getdatasuess(list);
            }

            @Override
            public void fail(Throwable e) {
                duanziview.getdatafail(e.toString());
            }
        });

       }
}
