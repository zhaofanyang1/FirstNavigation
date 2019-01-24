package com.jiyun.firstnavigation.utils;

import com.google.gson.Gson;
import com.jiyun.firstnavigation.beans.DownBean;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目名：MyViewDemo2
 * 包名：  com.jiyun.firstnavigation.utils
 * 文件名：HttpUtils
 * 创建者：liangxq
 * 创建时间：2019/1/18  9:51
 * 描述：TODO
 */
public class HttpUtils {

    public static RequestBody getBody(String json) {
        RequestBody requestBody = null;
        if (json != null) {
            requestBody = RequestBody.create(MediaType.parse(""), json);
        }
        return requestBody;
    }

    public static RequestBody getBody1(String json) {
        RequestBody requestBody = null;

        if (json != null) {
            requestBody = RequestBody.create(MediaType.parse("application/json,charset-UTF-8"), json);
        }
        return requestBody;
    }
}
