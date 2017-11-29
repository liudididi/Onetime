package m;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Fabiaomodle {
    public  void  fabiaodata(int uid ,String content,final requestfabiaoBack requestBack){
     /*   Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        map.put("content",content);*/
        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().faduanzi(uid,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<Basebean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(Basebean value) {
                      requestBack.success(value.msg,value.code);

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

    public  interface  requestfabiaoBack{
        void  success(String msg,String code);
        void  fail(Throwable e);
    }

}
