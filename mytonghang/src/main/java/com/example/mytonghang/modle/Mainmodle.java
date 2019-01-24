package com.example.mytonghang.modle;

import android.util.Log;

import com.example.mytonghang.RequestApi;
import com.example.mytonghang.data.Bean;
import com.example.mytonghang.data.Demo;
import com.example.mytonghang.https.HttpUtils;
import com.example.mytonghang.https.Myserver;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.PartMap;

public class Mainmodle implements Mymodle {


    @Override
    public void showData(final HttpFinish httpFinish, final RequestApi requestApi, Object[] t) {
        Myserver myserver = HttpUtils.getHttpUtils().getMyserver();
        switch (requestApi) {
            case TABTITLE:
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded,charset-UTF-8"), "");
                Observable<String> stringObservable = myserver.getTitles("listNewsChannel", requestBody);

                stringObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String value) {
                                Log.d("Mainmodle", "value1" + value.toString());
                                httpFinish.showList(value, requestApi);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Throwable", "Throwable" + e.getMessage().toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });


                break;
            case SOURCE:
                Bean bean = new Bean();
                bean.setCursor((int) t[0] + "");
                bean.setChannelId((String) t[1]);
                String s = new Gson().toJson(bean);
                RequestBody requestBody1 = RequestBody .create(MediaType.parse("application/json,charset-UTF-8"), s);
                Observable<String> demo = myserver.getDemo("downListNews", requestBody1);
                Log.e("22222222222", "zou");

                demo.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String value) {
                                Log.e("333333333333", value.toString());
                                httpFinish.showList(value, requestApi);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("onError", e.getMessage().toString());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
}
