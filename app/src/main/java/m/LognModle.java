package m;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.UserBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mybase.Basebean;
import okhttp3.ResponseBody;
import utils.MyQusetUtils;
import utils.SPUtils;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public class LognModle {
  public  void  getlogndata(String user, String pass, final requestBack requestBack){
      Map<String,Object> map=new HashMap<>();
      map.put("mobile",user);
      map.put("password",pass);
      new MyQusetUtils.Builder().addConverterFactory()
              .addCallAdapterFactory().build().getQuestInterface().login(map)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<Basebean<UserBean>>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(Basebean<UserBean> value) {
                      requestBack.logsuccess(value);
                  }

                  @Override
                  public void onError(Throwable e) {
                      requestBack.fail(e);
                  }

                  @Override
                  public void onComplete() {

                  }
              });
  }
    public  void  getuser(int  uid, String token, final requestBack requestBack){
        Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        map.put("token",token);
        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().getuser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Basebean<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Basebean<UserBean> value) {
                        requestBack.logsuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestBack.fail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

  public  interface  requestBack{
      void  logsuccess(Basebean<UserBean> value);
      void  fail(Throwable e);
  }

}
