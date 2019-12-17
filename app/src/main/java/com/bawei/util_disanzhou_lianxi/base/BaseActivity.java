package com.bawei.util_disanzhou_lianxi.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.util_disanzhou_lianxi.Contracts;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/14
 * Time: 11:04
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements Contracts.IView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
            mPresenter = initPresenter();
            mPresenter.onAttch(this);
            initView();
            startCoding();
        }
    }

    protected abstract void startCoding();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int getLayoutID();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDeAttch();
            mPresenter = null;
        }
    }
}
