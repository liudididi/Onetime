package present;

import m.Getdatamodle;
import mybase.Basepresent;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/1.
 * 邮箱：461211527@qq.com.
 */

public class Nichengp extends Basepresent {
    private Baseview baseview;
    private Getdatamodle getdatamodle;

    public Nichengp(Object viewmodel) {
        super(viewmodel);
        this.baseview= (Baseview) viewmodel;
        if(getdatamodle==null){
            getdatamodle=new Getdatamodle();
        }
    }
    public  void xiugai(int uid,String name){
        getdatamodle.changenicheng(uid, name, new Getdatamodle.XiuniBack() {
            @Override
            public void success(String code, String msg) {
                if(code.equals("0")){
                    baseview.success();
                }else {
                    baseview.fail(msg);
                }
            }

            @Override
            public void fail(Throwable e) {

            }
        });




    }


}
