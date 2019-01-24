package com.jiyun.firstnavigation.modle;

import android.util.Log;

import com.jiyun.firstnavigation.base.BaseObserver;
import com.jiyun.firstnavigation.beans.ListNewsChannel;
import com.jiyun.firstnavigation.contact.ListNewsChannelI;
import com.jiyun.firstnavigation.http.HttpManager;
import com.jiyun.firstnavigation.http.HttpResponse;
import com.jiyun.firstnavigation.utils.HttpUtils;
import com.jiyun.firstnavigation.utils.RxUtils;

public class IListNewsChannelM {
    public void getListNewsChanne(String json, final ListNewsChannelI.ListNewsChannelCallBak listNewsChannelCallBak) {
        HttpManager.getInstance().getServer().getListNewsChannel(HttpUtils.getBody(json)).compose(RxUtils.<HttpResponse<ListNewsChannel>>rxScheduleThread())
                .compose(RxUtils.<ListNewsChannel>handeResult())
                .subscribe(new BaseObserver<ListNewsChannel>(listNewsChannelCallBak) {
                    @Override
                    public void onNext(ListNewsChannel value) {
                       /* String channelName = value.getNewsChannelList().get(1).getChannelName();
                        Log.e("onNext--channelName", channelName);*/
                        listNewsChannelCallBak.setListNewsTab(value);
                    }
                });
    }
}
