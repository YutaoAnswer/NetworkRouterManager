package com.trigletop.networkroutermanager.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * WIFI设置
 */
public class WIFISettingFragment extends Fragment {

    public static WIFISettingFragment newInstance() {
        WIFISettingFragment extranetSettingFragment = new WIFISettingFragment();
        Bundle args = new Bundle();
        extranetSettingFragment.setArguments(args);
        return extranetSettingFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
