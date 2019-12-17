package com.bawei.util_disanzhou_lianxi.utils;

import android.widget.ImageView;

import com.bawei.util_disanzhou_lianxi.R;
import com.bawei.util_disanzhou_lianxi.app.MyApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:41
 */
public class GlideUtil {
    public static void loadImger(String url, ImageView imageView) {
        Glide.with(MyApp.mContext)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
