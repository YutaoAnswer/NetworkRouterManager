package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class WANPortSettingFragment extends Fragment {

    private Unbinder unbinder;

    private SiUtil siUtil;
    private static LocalApi mLocalApi;

    public static WANPortSettingFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortSettingFragment extranetSettingFragment = new WANPortSettingFragment();
        Bundle args = new Bundle();
        extranetSettingFragment.setArguments(args);
        return extranetSettingFragment;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wanport_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        initData();
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {
        siUtil = new SiUtil(getContext());
    }

    private void initView() {

    }

    private void initData() {
        siUtil.getWanTypeRet(mLocalApi);
    }


}
