package present;



import org.json.JSONObject;

import java.io.IOException;

import bean.UserBean;
import m.LognModle;
import mInterface.Lognview;
import mybase.Basebean;
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
            public void logsuccess(Basebean<UserBean> value) {
                String code = value.code;
                if(code.equals("0")){
                    UserBean data = value.data;
                    lognview.lognsuess(data);
                }else {
                    String msg = value.msg;
                    lognview.lognfail(msg);
                }
            }
            @Override
            public void fail(Throwable e) {
                lognview.fail(e.toString());
            }
        });
    }
    public  void  getuser(int uid,String token){
        lognModle.getuser(uid, token, new LognModle.requestBack() {
            @Override
            public void logsuccess(Basebean<UserBean> value) {
                String code = value.code;
                System.out.println("code=="+code);
                if(code.equals("0")){
                    lognview.lognsuess(value.data);
                }else if(code.equals("2")){
                    lognview.fail("身份过期，请重新登录");
                }else {
                    lognview.lognfail(value.msg);
                }
            }

            @Override
            public void fail(Throwable e) {

            }
        });
    }

}
