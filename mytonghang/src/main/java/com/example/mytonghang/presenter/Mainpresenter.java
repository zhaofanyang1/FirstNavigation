package com.example.mytonghang.presenter;

import android.support.v7.view.menu.MenuView;
import android.util.Log;

import com.example.mytonghang.RequestApi;
import com.example.mytonghang.data.Demo;
import com.example.mytonghang.data.NewsChannel;
import com.example.mytonghang.modle.Mymodle;
import com.example.mytonghang.view.Myview;
import com.google.gson.Gson;

public class Mainpresenter<T> implements Mypresenter<T>, Mymodle.HttpFinish {
    Myview myview;
    Mymodle mymodle;

    public Mainpresenter(Myview myview, Mymodle mymodle) {
        this.myview = myview;
        this.mymodle = mymodle;
    }

    @Override
    public void showList(Object o, RequestApi requestApi) {
        String string = (String) o;
        Gson gson = new Gson();
        if (myview != null) {
            switch (requestApi) {
                case TABTITLE:
                    NewsChannel newsChannel = gson.fromJson(string, NewsChannel.class);
                    //Log.d("Mainpresenter", "" + newsChannel.getData().getNewsChannelList().get(1).getChannelName());
                    myview.showList(newsChannel.getData().getNewsChannelList());
                    break;
                case SOURCE:
                    Log.e("Mainpresenter", "123456789");
                    Demo demo = gson.fromJson(string, Demo.class);
                    Log.e("Mainpresenter", "demo.getData().getNewList():" + demo.getData().getNewList());
                    myview.showList(demo.getData().getNewList());

                    break;
            }
        }
    }

    @Override
    public void showError(String error) {

    }


    @Override
    public void getData(RequestApi requestApi, T... t) {
        if (mymodle != null) {
            mymodle.showData(this, requestApi, t);
        }
    }
}
