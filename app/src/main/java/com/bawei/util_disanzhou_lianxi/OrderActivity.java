package com.bawei.util_disanzhou_lianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bawei.util_disanzhou_lianxi.adapter.MyAdapter;
import com.bawei.util_disanzhou_lianxi.base.BaseActivity;
import com.bawei.util_disanzhou_lianxi.base.BasePresenter;
import com.bawei.util_disanzhou_lianxi.bean.OrderBean;
import com.bawei.util_disanzhou_lianxi.presenter.PresenterImpl;
import com.bawei.util_disanzhou_lianxi.url.MyUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends BaseActivity {

    private RecyclerView recy_view;
    private MyAdapter myAdapter;
    private List<OrderBean.OrderListBean> mList = new ArrayList<>();

    @Override
    protected void startCoding() {

        myAdapter = new MyAdapter(this,mList);
        recy_view.setAdapter(myAdapter);

        Map map = new HashMap();
        map.put("status", "0");
        map.put("page", "1");
        map.put("count", "5");

        mPresenter.startGet(MyUrl.ORDERURL, map, OrderBean.class);
    }

    @Override
    protected void initView() {
        recy_view = (RecyclerView) findViewById(R.id.recy_view);
        recy_view.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order;
    }

    @Override
    public void onSuccess(Object o) {
        if(o instanceof OrderBean){
            mList.addAll(((OrderBean) o).getOrderList());
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String error) {

    }
}
