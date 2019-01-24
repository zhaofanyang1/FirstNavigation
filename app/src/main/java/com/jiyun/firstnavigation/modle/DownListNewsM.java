package com.jiyun.firstnavigation.modle;

import android.util.Log;

import com.jiyun.firstnavigation.base.BaseObserver;
import com.jiyun.firstnavigation.beans.DownListNews;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.contact.DownListNewsI;
import com.jiyun.firstnavigation.http.HttpManager;
import com.jiyun.firstnavigation.http.HttpResponse;
import com.jiyun.firstnavigation.utils.HttpUtils;
import com.jiyun.firstnavigation.utils.RxUtils;

public class DownListNewsM {
    public void getDownListNews(String json,final DownListNewsI.DownListNewsICallBak downListNewsICallBak) {
        HttpManager.getInstance().getServer().getDemo(HttpUtils.getBody(json)).compose(RxUtils.<HttpResponse<DownListNews>>rxScheduleThread())
                .compose(RxUtils.<DownListNews>handeResult())
                .subscribe(new BaseObserver<DownListNews>(downListNewsICallBak) {
                    @Override
                    public void onNext(DownListNews value) {
                        String title = value.getNewList().get(1).getTitle();
                        Log.e("DownListNewsM", title);
                        downListNewsICallBak.setDownListNewsI(value);
                    }
                });
    }
}
