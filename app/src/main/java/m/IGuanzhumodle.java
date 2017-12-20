package m;

import java.util.List;

import javax.inject.Inject;

import bean.GuanzhuBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/12/15.
 * 邮箱：461211527@qq.com.
 */

public class IGuanzhumodle {

    @Inject
    public IGuanzhumodle() {
    }
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public  void  getIguanzhudata(int uid, final RequestBack requestBack){
        DisposableSubscriber<Basebean<List<GuanzhuBean>>> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().Iguanzhudata(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Basebean<List<GuanzhuBean>>>() {
                    @Override
                    public void onNext(Basebean<List<GuanzhuBean>> listBasebean) {
                        requestBack.getdatasuccess(listBasebean);
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
        void  getdatasuccess(Basebean<List<GuanzhuBean>> listBasebean);
        void  fail(Throwable t);
    }

    public void   ondestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
