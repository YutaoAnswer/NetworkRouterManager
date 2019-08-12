package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

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

public class WANPortStaticIPAddressFragment extends Fragment {

    private static LocalApi mLocalApi;

    private Unbinder unbinder;

    public static WANPortStaticIPAddressFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortStaticIPAddressFragment wanPortStaticIPAddressFragment = new WANPortStaticIPAddressFragment();
        Bundle args = new Bundle();
        wanPortStaticIPAddressFragment.setArguments(args);
        return wanPortStaticIPAddressFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wanportstaticipaddress, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {

    }

    private void initView() {

    }

    private void initData() {

    }
}
