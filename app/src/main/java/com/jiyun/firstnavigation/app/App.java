package com.jiyun.firstnavigation.app;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        CrashReport.initCrashReport(getApplicationContext(), "2b93ddfa71", false);
        UMConfigure.setLogEnabled(true);
        //初始化
        UMConfigure.init(this, "5c43ddb7f1f5560d92000be3", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
    }

    public static App getApplication() {
        return app;
    }
}
