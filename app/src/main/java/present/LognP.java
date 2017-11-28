package present;



import org.json.JSONObject;

import java.io.IOException;

import m.LognModle;
import mInterface.Lognview;
import mybase.Basepresent;
import okhttp3.ResponseBody;


/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public class   LognP  extends Basepresent {
     private LognModle lognModle;
     private  Lognview lognview;
    public LognP(Lognview viewmode) {
        super(viewmode);
        this.lognview=viewmode;
        if(lognModle==null){
            lognModle=new LognModle();
        }
    }
    public  void  login(String user,String pass){
        lognModle.getlogndata(user, pass, new LognModle.requestBack() {


            @Override
            public void logsuccess(String code, String msg) {
                if(code.equals("0")){
                    lognview.lognsuess();
                }else {
                    lognview.lognfail(msg);
                }
            }

            @Override
            public void fail(Throwable e) {
                lognview.fail(e.toString());
            }
        });
    }

}
