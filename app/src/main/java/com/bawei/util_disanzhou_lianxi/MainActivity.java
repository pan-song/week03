package com.bawei.util_disanzhou_lianxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.util_disanzhou_lianxi.base.BaseActivity;
import com.bawei.util_disanzhou_lianxi.base.BasePresenter;
import com.bawei.util_disanzhou_lianxi.bean.LoginBean;
import com.bawei.util_disanzhou_lianxi.presenter.PresenterImpl;
import com.bawei.util_disanzhou_lianxi.url.MyUrl;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit_phone;
    private EditText edit_pwd;
    private Button login_btn;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void startCoding() {

    }

    @Override
    protected void initView() {
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        login_btn = (Button) findViewById(R.id.login_btn);

        sp = getSharedPreferences("pss", MODE_PRIVATE);
        edit = sp.edit();

        login_btn.setOnClickListener(this);
    }

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:

                String phone = edit_phone.getText().toString().trim();
                String pwd = edit_pwd.getText().toString().trim();

                if (phone.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.isEmpty()) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map map = new HashMap();
                map.put("phone", phone);
                map.put("pwd", pwd);

                mPresenter.startPost(MyUrl.LOGINURL, map, LoginBean.class);

                break;
        }
    }


    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginBean) {
            Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();

            edit.putString("userId", ((LoginBean) o).getResult().getUserId() + "");
            edit.putString("sessionId", ((LoginBean) o).getResult().getSessionId() + "");
            edit.commit();

            startActivity(new Intent(this, OrderActivity.class));
            finish();
        }
    }

    @Override
    public void onError(String error) {

    }
}
