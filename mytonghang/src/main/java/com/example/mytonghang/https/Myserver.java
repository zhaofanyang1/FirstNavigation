package com.example.mytonghang.https;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Myserver {
    String url = "https://www.firstgainfo.com/firstga/app/news/";

    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Observable<String> getTitles(@Url String url, @Body RequestBody requestBody);


    @POST
    @Headers("Content-Type:application/json")
    Observable<String> getDemo(@Url String url, @Body RequestBody requestBody);
}
