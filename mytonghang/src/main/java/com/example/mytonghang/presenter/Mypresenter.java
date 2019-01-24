package com.example.mytonghang.presenter;

import com.example.mytonghang.RequestApi;

public interface Mypresenter<T> {
    void getData( RequestApi requestApi, T... t);
}
