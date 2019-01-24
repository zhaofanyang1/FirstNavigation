package com.jiyun.firstnavigation.http;

import com.jiyun.firstnavigation.beans.DownListNews;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.beans.UploadHeadImage;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServer {
    /*
     * 2.2	新闻频道
     */

    @POST("news/listNewsChannel")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<HttpResponse<ListNewsChannel>> getListNewsChannel(@Body RequestBody requestBody);

    /*
     * 2.4	加载新闻列表
     */
    @POST("news/downListNews")
    @Headers("Content-Type:application/json")
    Observable<HttpResponse<DownListNews>> getDemo(@Body RequestBody requestBody);












    /*
     *1.13	上传头像(upload)
     */
    @POST("users/uploadHeadImage")
    @Headers("multipart/form-data")
    Observable<UploadHeadImage> getupload(@Body RequestBody requestBody);

}
