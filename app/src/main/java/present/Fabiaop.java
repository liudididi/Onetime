package present;

import m.Fabiaomodle;
import mybase.Basepresent;
import mybase.Baseview;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Fabiaop  extends Basepresent{
    private Fabiaomodle fabiaomodle;
    private Baseview baseview;

    public Fabiaop(Object viewmodel) {
        super(viewmodel);
        if(fabiaomodle==null){
            fabiaomodle=new Fabiaomodle();
        }
        this.baseview= (Baseview) viewmodel;
    }

    public  void  fabiao(int uid,String content){
        fabiaomodle.fabiaodata(uid, content, new Fabiaomodle.requestfabiaoBack() {
            @Override
            public void success(String msg, String code) {

                System.out.println("msg==="+msg+code);
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
