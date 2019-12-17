package com.bawei.util_disanzhou_lianxi.api;

import com.bawei.util_disanzhou_lianxi.url.MyUrl;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 10:49
 */
public interface ApiService {
    @POST
    Observable<ResponseBody> onOrderInfo(@Url String url, @QueryMap Map<String, Object> map);

    @GET
    Observable<ResponseBody> onGetInfo(@Url String url, @QueryMap Map<String, Object> map);
}
