package present;

import java.util.List;

import bean.Guanggao;
import m.Getdatamodle;
import mInterface.Getadv;
import mybase.Basepresent;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/12/1.
 * 邮箱：461211527@qq.com.
 */

public class Getadp  extends Basepresent{
    private Getdatamodle getdatamodle;
    private Getadv getadv;
    public Getadp(Object viewmodel) {
        super(viewmodel);
        this.getadv= (Getadv) viewmodel;
        if(getdatamodle==null){
            getdatamodle=new Getdatamodle();
        }
    }

   public  void getad(){
       getdatamodle.getad(new Getdatamodle.AdBack() {
           @Override
           public void success(List<Guanggao> list ,String  msg,String code) {
               if(code.equals("0")){
                   getadv.adsuccess(list);
               }else {
                   getadv.adfail(msg);
               }
           }
           @Override
           public void fail(Throwable e) {
               getadv.adfail(e.toString());
           }
       });
}



}
