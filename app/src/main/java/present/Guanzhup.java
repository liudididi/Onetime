package present;

import javax.inject.Inject;

import m.GuanzhuModle;
import mybase.Basebean;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class Guanzhup {
    @Inject
    GuanzhuModle guanzhuModle;
    private Baseview baseview;
     @Inject
    public Guanzhup(Baseview baseview) {
        this.baseview = baseview;
    }
    public  void  guanzhu(int uid,String follwid){
         if(guanzhuModle==null){
             guanzhuModle=new GuanzhuModle();
         }
         guanzhuModle.guanzhu(uid, follwid, new GuanzhuModle.RequestBack() {
             @Override
             public void logsuccess(Basebean bean) {
                 if(bean.code.equals("0")){
                     baseview.success();
                 }else {
                     baseview.fail(bean.msg);

                 }

             }
             @Override
             public void fail(Throwable t) {
                 baseview.fail(t.toString());
             }
         });
    }
}
