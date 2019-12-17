package com.bawei.util_disanzhou_lianxi;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 10:57
 */
public interface Contracts {

    interface IModel{
        void onGetInfo(String url,Map<String,Object> map,Class cla,MyCallBack myCallBack);
        void onPostInfo(String url, Map<String,Object> map,Class cla,MyCallBack myCallBack);
    }

    interface IView<T>{
        void onSuccess(T t);
        void onError(String error);
    }

    interface IPresenter{
        void startGet(String url,Map<String,Object> map,Class cla);
        void startPost(String url,Map<String,Object> map,Class cla);
    }

    interface MyCallBack<T>{
        void onSuccess(T t);
        void onError(String error);
    }
}
