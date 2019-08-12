package com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class WirelessTypeTwoFragment extends Fragment {

    private static LocalApi mLocalApi;

    private Unbinder unbinder;

    public static WirelessTypeTwoFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WirelessTypeTwoFragment wirelessTypeTwiFragment = new WirelessTypeTwoFragment();
        Bundle args = new Bundle();
        wirelessTypeTwiFragment.setArguments(args);
        return wirelessTypeTwiFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wireless_host, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
