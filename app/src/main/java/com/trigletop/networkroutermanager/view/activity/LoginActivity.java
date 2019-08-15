package com.trigletop.networkroutermanager.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.trigletop.networkroutermanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

/**
 * 登录页
 */
public class LoginActivity extends Activity {

    @BindView(R.id.et_password)
    EditText etPassword;
    private LocalApi localApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        localApiInit();
    }

    private void init() {
        //路由器版本信息
        localApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);
        //设置路由器的ip地址或域名
        localApi.setmLocalIp("192.168.4.1");
        //路由器管理员密码
        localApi.setAdminPassword("admin");
    }

    /**
     * 初始化
     */
    private void localApiInit() {
        //路由器版本信息
        localApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);
        //设置路由器的ip地址或域名
        localApi.setmLocalIp("192.168.4.1");
        //路由器管理员密码
        localApi.setAdminPassword("admin");

    }

    @OnClick({R.id.btn_login, R.id.btn_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String password = etPassword.getText().toString();
                if (!password.isEmpty() && password.equals("admin")) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.btn_forget_password:

                break;
        }
    }
}
