package m;

import java.util.List;

import javax.inject.Inject;

import bean.Duanzibean;
import bean.UserBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class GuanzhuModle {
    @Inject
    public GuanzhuModle() {
    }
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
   public  void  guanzhu(int uid , String follwid, final RequestBack requestBack){
    DisposableSubscriber<Basebean> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
            .addCallAdapterFactory().build().getQuestInterface().guanzhu(uid, follwid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSubscriber<Basebean>() {
                @Override
                public void onNext(Basebean basebean) {
                    requestBack.logsuccess(basebean);
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
    public  interface  RequestBack{
        void  logsuccess(Basebean bean);
        void  fail(Throwable t);
    }

    public void   ondestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
