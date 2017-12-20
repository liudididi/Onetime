package m;

/**
 * Created by 地地 on 2017/12/16.
 * 邮箱：461211527@qq.com.
 */

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
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Sousuomodle {
    @Inject
    public Sousuomodle() {
    }
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public  void  sousuodata(String keywords, String page, final RequestBack  requestBack){
        DisposableSubscriber<Basebean<List<GuanzhuBean>>> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().sousuo(keywords,page)
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

