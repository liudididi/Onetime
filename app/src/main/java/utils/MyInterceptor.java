package utils;

import android.util.Log;

import com.liu.asus.yikezhong.Myapp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
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
            Log.d(TAG,"----------Start----------------");
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
                }
            }

      /*   String content = proceed.body().string();
             long duration=endTime-startTime;
            Log.d(TAG, "| Response:" + content);
            Log.d(TAG,"----------End:"+duration+"毫秒----------");*/
            return chain.proceed(request);
    }
}
