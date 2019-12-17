package com.bawei.util_disanzhou_lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.util_disanzhou_lianxi.R;
import com.bawei.util_disanzhou_lianxi.bean.OrderBean;
import com.bawei.util_disanzhou_lianxi.utils.GlideUtil;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/12/15
 * Time: 13:48
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private Context mContext;
    private List<OrderBean.OrderListBean> mList;

    public MyAdapter(Context mContext, List<OrderBean.OrderListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Holder holder = null;
        View view = null;
        view = View.inflate(mContext, R.layout.order_recy, null);
        holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        OrderBean.OrderListBean orderListBean = mList.get(i);

        holder.order_text.setText(orderListBean.getOrderId() + "");
        if (orderListBean.getOrderStatus() == 1) {
            holder.cancel_text.setText("取消订单");
            holder.pay_text.setText("去支付");
        } else if (orderListBean.getOrderStatus() == 2) {
            holder.cancel_text.setVisibility(View.GONE);
            holder.pay_text.setText("去支付");
        }

        OrderAdapter orderAdapter = new OrderAdapter(mContext, mList.get(i).getDetailList());
        holder.order_recy.setAdapter(orderAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView order_text;
        RecyclerView order_recy;
        Button cancel_text, pay_text;

        public Holder(@NonNull View itemView) {
            super(itemView);

            order_text = itemView.findViewById(R.id.order_text);
            order_recy = itemView.findViewById(R.id.order_recy);
            cancel_text = itemView.findViewById(R.id.cancel_text);
            pay_text = itemView.findViewById(R.id.pay_text);

            order_recy.setLayoutManager(new LinearLayoutManager(mContext));
        }
    }

    class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Holder> {

        private Context mContext;
        private List<OrderBean.OrderListBean.DetailListBean> mList;

        public OrderAdapter(Context mContext, List<OrderBean.OrderListBean.DetailListBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        @NonNull
        @Override
        public OrderAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            Holder holder = null;
            View view = null;
            view = View.inflate(mContext, R.layout.recy_item, null);
            holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull OrderAdapter.Holder holder, int i) {

            String[] split = mList.get(i).getCommodityPic().split(",");
            if (split != null && split.length > 0) {
                GlideUtil.loadImger(split[0], holder.commodityPic);
            } else {
                GlideUtil.loadImger(mList.get(i).getCommodityPic(), holder.commodityPic);
            }
            holder.commodityName.setText(mList.get(i).getCommodityName() + "");
            holder.commodityPrice.setText(mList.get(i).getCommodityPrice() + "");

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {

            ImageView commodityPic;
            TextView commodityName, commodityPrice;

            public Holder(@NonNull View itemView) {
                super(itemView);
                commodityPic = itemView.findViewById(R.id.commodityPic);
                commodityName = itemView.findViewById(R.id.commodityName);
                commodityPrice = itemView.findViewById(R.id.commodityPrice);
            }
        }
    }
}
