package utils;

import android.util.Log;

import com.liu.asus.yikezhong.Myapp;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by asus on 2017/11/5.
 */

public class MyInterceptor implements Interceptor {
    public String TAG = "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String  token = (String) SPUtils.get(Myapp.context, "token", "");
            Log.d(TAG,"\n");
            Log.d(TAG,"----------Start----------------"+token);

            String method=request.method();
            if("POST".equals(method)){
                FormBody.Builder sb = new FormBody.Builder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.add(body.encodedName(i) , body.encodedValue(i));
                    }
                    body=sb.add("source","android").add("appVersion","101")
                            .add("token",token)
                            .build();
                    request=request.newBuilder().post(body).build();
                    Log.d(TAG, "| "+request.toString());
                }else {
                    MultipartBody body=(MultipartBody)request.body();
                    MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    build  .addFormDataPart("source","android");
                    build  .addFormDataPart("appVersion","101");
                    build  .addFormDataPart("token",token);
                    List<MultipartBody.Part> parts = body.parts();
                    for (MultipartBody.Part part : parts) {
                        build.addPart(part);
                    }
                    request =request.newBuilder().post(build.build()).build();
                }
            }

        Response proceed = chain.proceed(request);
        if(MyNetUtils.isConnected(Myapp.context)){
            return proceed.newBuilder()
                    .header("Cache-Control", "public, max-age=" +60 * 60*24)
                    .removeHeader("Pragma")
                    .build();

        }else {
            return proceed.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 28)
                    .removeHeader("Pragma")
                    .build();
        }

      /*   String content = proceed.body().string();
             long duration=endTime-startTime;
            Log.d(TAG, "| Response:" + content);
            Log.d(TAG,"----------End:"+duration+"毫秒----------");*/

    }
}
