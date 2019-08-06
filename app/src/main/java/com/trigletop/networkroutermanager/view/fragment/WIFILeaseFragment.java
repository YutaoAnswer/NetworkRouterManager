package com.trigletop.networkroutermanager.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

/**
 * WIFI租赁
 */
public class WIFILeaseFragment extends Fragment {

    public static WIFILeaseFragment newInstance() {
        WIFILeaseFragment extranetSettingFragment = new WIFILeaseFragment();
        Bundle args = new Bundle();
        extranetSettingFragment.setArguments(args);
        return extranetSettingFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wifilease, container, false);
    }
}
