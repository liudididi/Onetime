package m;

import java.util.List;

import javax.inject.Inject;

import bean.GuanzhuBean;
import bean.TuijianBean;
import fragment.Tuijian;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/12/18.
 * 邮箱：461211527@qq.com.
 */

public class Xiangqm {
    @Inject
    public Xiangqm() {
    }
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public  void    getXiangqing(int wid, final XqRequestBack xqRequestBack){
        DisposableSubscriber<Basebean<TuijianBean>> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().Xiangqing(wid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Basebean<TuijianBean>>() {
                    @Override
                    public void onNext(Basebean<TuijianBean> tuijianBasebean) {
                          xqRequestBack.getdatasuccess(tuijianBasebean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        xqRequestBack.fail(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }
    public  interface  XqRequestBack{
        void  getdatasuccess(Basebean<TuijianBean> Basebean);
        void  fail(Throwable t);
    }

    public void   ondestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

}
