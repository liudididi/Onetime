package mInterface;

import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import bean.UserBean;
import io.reactivex.Observable;
import mybase.Basebean;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public interface QuestInterface {
    @POST("/user/login")
    @FormUrlEncoded
    Observable<Basebean<UserBean>> login(@FieldMap Map<String, Object> maps);

    @POST("/quarter/getJokes")
    @FormUrlEncoded
    Observable<Basebean<List<Duanzibean>>> getdata(@Field("page") int page);

    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<Basebean<UserBean>> getuser(@FieldMap Map<String, Object> maps);




}