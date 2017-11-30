package present;

import java.util.List;

import m.Fabiaomodle;
import mInterface.Faduanziv;
import mybase.Basepresent;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Fabiaop  extends Basepresent{
    private Fabiaomodle fabiaomodle;
    private Faduanziv baseview;

    public Fabiaop(Object viewmodel) {
        super(viewmodel);
        if(fabiaomodle==null){
            fabiaomodle=new Fabiaomodle();
        }
        this.baseview= (Faduanziv) viewmodel;
    }

    public  void  fabiao(int uid, String content, List<String> list){
        fabiaomodle.fabiaodata(uid, content,list,new Fabiaomodle.requestfabiaoBack() {
            @Override
            public void success(String msg, String code) {
                if(code.equals("0")){
                    baseview.fabusuccess();
                }else if(code.equals("2")){
                    baseview.tokenout(msg);
                }else {
                    baseview.fabufail(msg);
                }
            }

            @Override
            public void fail(Throwable e) {

            }
        });

    }
}
