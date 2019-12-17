package com.bawei.util_disanzhou_lianxi.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:42
 */
public class MyApp extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
