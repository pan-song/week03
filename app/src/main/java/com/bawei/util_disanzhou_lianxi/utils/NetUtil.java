package com.bawei.util_disanzhou_lianxi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.util_disanzhou_lianxi.api.ApiService;
import com.bawei.util_disanzhou_lianxi.app.MyApp;
import com.bawei.util_disanzhou_lianxi.url.MyUrl;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 10:51
 */
public class NetUtil {

    private ApiService apiService;

    private NetUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        SharedPreferences pss = MyApp.mContext.getSharedPreferences("pss", Context.MODE_PRIVATE);
                        String userId = pss.getString("userId", "");
                        String sessionId = pss.getString("sessionId", "");
                        if (userId.isEmpty() && sessionId.isEmpty()) {
                            return chain.proceed(chain.request());
                        } else {
                            Request request = chain.request().newBuilder()
                                    .addHeader("userId", userId)
                                    .addHeader("sessionId", sessionId)
                                    .build();
                            return chain.proceed(request);
                        }

                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyUrl.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @SuppressLint("CheckResult")
    public void onPostInfo(String url, Map<String, Object> map, final Class cla, final NetCallBack netCallBack) {
        apiService.onOrderInfo(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        if (netCallBack != null) {
                            Object o = gson.fromJson(responseBody.string(), cla);
                            netCallBack.onSuccess(o);
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void doGetInfo(String url, Map<String, Object> map, final Class cla, final NetCallBack netCallBack) {
        apiService.onGetInfo(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Gson gson = new Gson();
                        if (netCallBack != null) {
                            Object o = gson.fromJson(responseBody.string(), cla);
                            netCallBack.onSuccess(o);
                        }
                    }
                });
    }

    private static class NetHolder {
        private static NetUtil netUtil = new NetUtil();
    }

    public static NetUtil getInstance() {
        return NetHolder.netUtil;
    }


    public interface NetCallBack<T> {
        void onSuccess(T t);

        void onError(String error);
    }

}
