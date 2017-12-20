package present;

import m.Pinglunm;
import mybase.Basebean;
import mybase.Basepresent;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/19.
 * 邮箱：461211527@qq.com.
 */

public class Pinglunp extends Basepresent {
    private Pinglunm pinglunm;
     private Baseview baseview;

    public Pinglunp(Object viewmodel) {
        super(viewmodel);
        if(pinglunm==null){
            pinglunm=new Pinglunm();
        }
        this.baseview= (Baseview) viewmodel;
    }

    public  void  Pinglun(int uid,int wid,String count){
        pinglunm.getpinglun(uid, wid, count, new Pinglunm.requestBack() {
            @Override
            public void pinlunsuccess(Basebean basebean) {
                if(basebean.code.equals("0")){
                    baseview.success();
                }else {
                    baseview.fail(basebean.msg);
                }
            }

            @Override
            public void fail(Throwable t) {
                baseview.fail(t.toString());
            }
        });
    }
    public void  zhuxiao(){
        pinglunm.ondestory();
    }
}
