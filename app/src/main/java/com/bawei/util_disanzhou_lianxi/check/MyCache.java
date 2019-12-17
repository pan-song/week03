package com.bawei.util_disanzhou_lianxi.check;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:48
 */
public class MyCache implements GlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int mySizeCaChe = 1024 * 1024 * 20;
        File file = new File(Environment.getExternalStorageDirectory(), "imagecache");
        String path = file.getPath();
        builder.setDiskCache(new DiskLruCacheFactory(path, mySizeCaChe));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}
