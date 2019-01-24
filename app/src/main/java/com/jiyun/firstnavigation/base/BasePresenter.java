package com.jiyun.firstnavigation.base;

import java.lang.ref.WeakReference;

/**
 * 项目名：MyViewDemo2
 * 包名：  com.jiyun.firstnavigation.base
 * 文件名：BasePresenter
 * 创建者：liangxq
 * 创建时间：2019/1/18  9:29
 * 描述：TODO
 */
public class BasePresenter<V> {


    private WeakReference<V> weakReference;

    public V mView;

    public void attachView(V v) {
        weakReference = new WeakReference(v);
        if(weakReference!=null){
            mView = weakReference.get();
        }

    }


    public void destoryView() {
        if (weakReference != null) {
            weakReference.clear();
        }
    }

}
