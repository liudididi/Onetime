package m;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import utils.MyQusetUtils;

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
              .subscribe(new Observer<ResponseBody>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(ResponseBody value) {
              requestBack.success(value);
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
      void  success(ResponseBody value);
      void  fail(Throwable e);
  }

}
