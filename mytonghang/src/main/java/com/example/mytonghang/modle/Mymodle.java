package com.example.mytonghang.modle;

import com.example.mytonghang.RequestApi;

public interface Mymodle<T> {
    interface HttpFinish<T> {
        void showList(T t, RequestApi requestApi);

        void showError(String error);
    }

    void showData(HttpFinish httpFinish, RequestApi requestApi, T... t);
}
