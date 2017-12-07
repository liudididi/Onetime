package mInterface;

import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import bean.Guanggao;
import bean.TuijianBean;
import bean.UserBean;
import fragment.Tuijian;
import io.reactivex.Observable;
import mybase.Basebean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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



    @Multipart
    @POST("/quarter/publishJoke")
    Observable<Basebean> faduanzi(@Part()  List<MultipartBody.Part>  file);

    @Multipart
    @POST("/file/upload")
    Observable<Basebean> changeicon(@Part()  List<MultipartBody.Part>  file);


    @POST("/user/updateNickName")
    @FormUrlEncoded
    Observable<Basebean> xiunicheng(@Field("uid") int uid,@Field("nickname") String nickname);

    @GET("/quarter/getAd")
    Observable<Basebean<List<Guanggao>>> getad();


    @POST("/quarter/getVideos")
    @FormUrlEncoded
    Observable<Basebean<List<TuijianBean>>> gettuijian(@Field("uid") String uid, @Field("type") int type, @Field("page") int page);


    @Multipart
    @POST("/quarter/publishVideo")
    Observable<Basebean> upvideo(@Part()  List<MultipartBody.Part>  file);


}