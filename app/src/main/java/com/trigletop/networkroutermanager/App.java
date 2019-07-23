package com.trigletop.networkroutermanager;

import android.app.Application;
import com.trigletop.networkroutermanager.Bean.Data;
import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSiRouterSDK();
    }

    /**
     * 矽昌SDK 初始化
     */
    private void initSiRouterSDK(){
        SiWiFiManager.init(this, Data.appKey,Data.appSecret);
    }
}
