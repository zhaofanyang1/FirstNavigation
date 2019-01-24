package com.jiyun.firstnavigation.utils;


import com.jiyun.firstnavigation.http.ApiException;
import com.jiyun.firstnavigation.http.HttpResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    //网络请求线程的切换
    public static <T> ObservableTransformer<T, T> rxScheduleThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    //将我们封装的公共实体类转换成我们需要的实体类对象(T),并且用Observer包裹，便于向下继续发送
    public static <T> ObservableTransformer<HttpResponse<T>, T> handeResult() {
        return new ObservableTransformer<HttpResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResponse<T>> upstream) {
                return upstream.flatMap(new Function<HttpResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(HttpResponse<T> tHttpResponse) throws Exception {
                        if (tHttpResponse.getCode() == 0) {
                            //创建T对象的Observable
                            return createData(tHttpResponse.getData());
                        } else {
                            //将服务器错误信息转换到我们的Error中
                            return Observable.error(new ApiException(tHttpResponse.getCode(), tHttpResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }

    private static <T> ObservableSource<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

}
