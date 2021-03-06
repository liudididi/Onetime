package mInterface;

import java.util.List;
import java.util.Map;

import bean.Duanzibean;
import bean.Guanggao;
import bean.GuanzhuBean;
import bean.TuijianBean;
import bean.UserBean;
import bean.VisionBean;
import fragment.Tuijian;
import io.reactivex.Flowable;
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
    Flowable<Basebean<UserBean>> login(@FieldMap Map<String, Object> maps);

    @GET("/quarter/getJokes")
    Observable<Basebean<List<Duanzibean>>> getdata(@Query("page") int page);

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


    @GET("/quarter/getVideos")

    Observable<Basebean<List<TuijianBean>>> gettuijian(@Query("uid") String uid, @Query("type") int type, @Query("page") int page);


    @Multipart
    @POST("/quarter/publishVideo")
    Observable<Basebean> upvideo(@Part()  List<MultipartBody.Part>  file);

    @GET("/quarter/getHotVideos")
    Observable<Basebean<List<TuijianBean>>> spremen(@Query("page") int page);


    @POST("/quarter/getUserVideos")
    @FormUrlEncoded
    Flowable<Basebean<List<TuijianBean>>> getuserdata(@Field("uid") int uid,@Field("page") int page);

     @GET("/quarter/getVersion")
     Flowable<Basebean<VisionBean>> upvision();

    @GET("/quarter/follow")
    Flowable<Basebean> guanzhu(@Query("uid") int uid,@Query("followId") String followId);

     @GET("/quarter/getFollowUsers")
    Flowable<Basebean<List<GuanzhuBean>>> Iguanzhudata(@Query("uid") int uid);

    @GET("/quarter/searchFriends")
    Flowable<Basebean<List<GuanzhuBean>>> sousuo(@Query("keywords") String keywords,@Query("page") String page);

    @GET("/quarter/getVideoDetail")
    Flowable<Basebean<TuijianBean>> Xiangqing(@Query("wid") int wid);

    @POST("/quarter/comment")
    @FormUrlEncoded
    Flowable<Basebean>  pinglun(@FieldMap Map<String, Object> maps);


}