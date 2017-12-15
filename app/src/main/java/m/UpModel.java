package m;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import bean.UserBean;
import bean.VisionBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import mybase.Basebean;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/12/14.
 * 邮箱：461211527@qq.com.
 */

public class UpModel  {
     private CompositeDisposable compositeDisposable;

    @Inject
    public UpModel() {
    }
    public   void  upvision(final UpVisonBack upVisonBack){
        compositeDisposable=new CompositeDisposable();
        DisposableSubscriber<Basebean<VisionBean>> disposableSubscriber = new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().upvision()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Basebean<VisionBean>>() {
                    @Override
                    public void onNext(Basebean<VisionBean> visionBeanBasebean) {
                        upVisonBack.upvisionccess(visionBeanBasebean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposableSubscriber);


    }
    public  interface  UpVisonBack{
        void  upvisionccess(Basebean<VisionBean> visionBeanBasebean);
        void  fail(Throwable t);
    }
    public void   ondestory(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
