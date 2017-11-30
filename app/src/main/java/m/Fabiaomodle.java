package m;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mybase.Basebean;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import utils.MyQusetUtils;

import static java.lang.String.valueOf;

/**
 * Created by 地地 on 2017/11/28.
 * 邮箱：461211527@qq.com.
 */

public class Fabiaomodle {
    public  void  fabiaodata(int uid ,String content,List<String> path,final requestfabiaoBack requestBack){
     /*   Map<String,Object> map=new HashMap<>();
        map.put("uid",uid);
        map.put("content",content);*/

        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        build.addFormDataPart("uid",uid+"");
        build.addFormDataPart("content",content);
        for (int i = 0; i <path.size() ; i++) {
            File file=new File(path.get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            build.addFormDataPart("jokeFiles", file.getName(), requestFile);
        }
        List<MultipartBody.Part> parts = build.build().parts();
             new MyQusetUtils.Builder().addConverterFactory()
                .addCallAdapterFactory().build().getQuestInterface().faduanzi(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<Basebean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(Basebean value) {

                      System.out.println("msgaa==="+value.msg);
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
