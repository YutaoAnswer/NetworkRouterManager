package com.trigletop.networkroutermanager;

import android.app.Application;

import com.trigletop.networkroutermanager.Bean.Data;

import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;

public class NetworkRouterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSiRouterSDK();
    }

    /**
     * 初始化矽昌Android 路由管理SDK
     */
    private void initSiRouterSDK() {
        SiWiFiManager.init(this, Data.appKey, Data.appSecret);
    }
}
