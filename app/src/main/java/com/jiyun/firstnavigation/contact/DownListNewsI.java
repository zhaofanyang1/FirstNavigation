package com.jiyun.firstnavigation.contact;

import com.jiyun.firstnavigation.base.BaseView;
import com.jiyun.firstnavigation.beans.DownListNews;
import com.jiyun.firstnavigation.http.HttpFinishCallbak;

public interface DownListNewsI {
    interface DownListNewsIV extends BaseView {
        void showDownListNews(DownListNews downListNews);

        void showError(String error);
    }


    interface DownListNewsP {
        void getDownListNews(String json);
    }

     interface DownListNewsICallBak extends HttpFinishCallbak {
        void setDownListNewsI(DownListNews downListNews);
    }
}
