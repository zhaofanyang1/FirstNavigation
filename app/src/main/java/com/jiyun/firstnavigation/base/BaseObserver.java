package com.jiyun.firstnavigation.base;

import android.util.Log;

import com.jiyun.firstnavigation.http.ApiException;
import com.jiyun.firstnavigation.http.HttpFinishCallbak;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<T> {
    private HttpFinishCallbak httpFinishCallbak;

    public BaseObserver(HttpFinishCallbak httpFinishCallbak) {
        this.httpFinishCallbak = httpFinishCallbak;
    }

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("liangxq", e.getMessage());
        String error = null;
        //判断是否是我们自定的(服务器返回的错误信息)错误信息
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            switch (apiException.getCode()) {
                case 2001:
                case 2002:
                    error = "网络错误";
                    break;
            }

        } else if (e instanceof HttpException) {
            error = "网络错误";
        }

        if(httpFinishCallbak!=null){
            httpFinishCallbak.errorShow(error);
        }

    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
