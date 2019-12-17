package com.bawei.util_disanzhou_lianxi.model;

import com.bawei.util_disanzhou_lianxi.Contracts;
import com.bawei.util_disanzhou_lianxi.utils.NetUtil;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:18
 */
public class ModelImpl implements Contracts.IModel {
    @Override
    public void onGetInfo(String url, Map<String, Object> map, Class cla, final Contracts.MyCallBack myCallBack) {
        NetUtil.getInstance().doGetInfo(url, map, cla, new NetUtil.NetCallBack() {
            @Override
            public void onSuccess(Object o) {
                myCallBack.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.onError(error);
            }
        });
    }

    @Override
    public void onPostInfo(String url, Map<String, Object> map, Class cla, final Contracts.MyCallBack myCallBack) {
        NetUtil.getInstance().onPostInfo(url, map, cla, new NetUtil.NetCallBack() {
            @Override
            public void onSuccess(Object o) {
                myCallBack.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                myCallBack.onError(error);
            }
        });
    }
}
