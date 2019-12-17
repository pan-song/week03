package com.bawei.util_disanzhou_lianxi.presenter;

import com.bawei.util_disanzhou_lianxi.Contracts;
import com.bawei.util_disanzhou_lianxi.R;
import com.bawei.util_disanzhou_lianxi.base.BasePresenter;
import com.bawei.util_disanzhou_lianxi.model.ModelImpl;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:19
 */
public class PresenterImpl extends BasePresenter {

    private ModelImpl model;

    @Override
    protected void initModel() {
        model = new ModelImpl();
    }

    @Override
    public void startGet(String url, Map<String, Object> map, Class cla) {
        model.onGetInfo(url, map, cla, new Contracts.MyCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void startPost(String url, Map<String, Object> map, Class cla) {
        model.onPostInfo(url, map, cla, new Contracts.MyCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }
}
