package m;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import bean.Guanggao;
import bean.TuijianBean;
import bean.UserBean;
import fragment.Tuijian;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mybase.Basebean;
import okhttp3.ResponseBody;
import utils.MyQusetUtils;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Getdatamodle {
    public  void  getduanzidata(int page ,final requestBack requestBack){
        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().getdata(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Basebean<List<Duanzibean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Basebean<List<Duanzibean>> value) {
                           requestBack.success(value.data);
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
    public  void  getad(final AdBack adBack){
        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().getad()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Basebean<List<Guanggao>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Basebean<List<Guanggao>> value) {
                        adBack.success(value.data,value.msg,value.code);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public  void  changenicheng(int uid ,String nickname,final XiuniBack requestBack){
        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().xiunicheng(uid,nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Basebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Basebean value) {
                        requestBack.success(value.code,value.msg);
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
    public  void  gettuijian(String uid, int type, int page, final requesttuijianBack requesttuijianBack){

        new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().gettuijian(uid,type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Basebean<List<TuijianBean>>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Basebean<List<TuijianBean>> value) {
                    requesttuijianBack.success(value.data);
                }

                @Override
                public void onError(Throwable e) {
                    requesttuijianBack.fail(e);
                }
                @Override
                public void onComplete() {

                }
            });






    }





    public  interface  requestBack{
        void  success(List<Duanzibean> list);
        void  fail(Throwable e);
    }
    public  interface  XiuniBack{
        void  success(String code,String msg);
        void  fail(Throwable e);
    }
    public  interface  AdBack{
        void  success(List<Guanggao> list,String msg ,String code);
        void  fail(Throwable e);
    }


    public  interface  requesttuijianBack{
        void  success(List<TuijianBean> list);
        void  fail(Throwable e);
    }




}
