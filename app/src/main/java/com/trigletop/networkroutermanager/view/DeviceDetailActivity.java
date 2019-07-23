package com.trigletop.networkroutermanager.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trigletop.networkroutermanager.utils.SiUtil;

public class DeviceDetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SiUtil siUtil = new SiUtil(this);
        siUtil.getSiRouterDeviceDetail();
    }
}
