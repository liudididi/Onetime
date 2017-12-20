package m;

import java.util.HashMap;
import java.util.Map;

import bean.UserBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/12/19.
 * 邮箱：461211527@qq.com.
 */

public class Pinglunm {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public  void  getpinglun(int uid, int wid, String content, final requestBack requestBack ){
        Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        map.put("wid",wid);
        map.put("content",content);
        DisposableSubscriber<Basebean> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().pinglun(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Basebean>() {
                    @Override
                    public void onNext(Basebean basebean) {
                        requestBack.pinlunsuccess(basebean);
                    }

                    @Override
                    public void onError(Throwable t) {
                     requestBack.fail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }

    public  interface  requestBack{
        void  pinlunsuccess(Basebean basebean);
        void  fail(Throwable t);
    }
    public void   ondestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
