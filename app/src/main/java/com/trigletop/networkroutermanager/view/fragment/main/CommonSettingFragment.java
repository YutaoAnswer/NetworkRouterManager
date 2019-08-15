package com.trigletop.networkroutermanager.view.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;
import com.trigletop.networkroutermanager.view.fragment.common.DevicesManagementFragment;
import com.trigletop.networkroutermanager.view.fragment.common.NetworkManagementFragment;
import com.trigletop.networkroutermanager.view.fragment.common.WirelessSettingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;


public class CommonSettingFragment extends Fragment {

    private static final String TAG = CommonSettingFragment.class.getSimpleName();

    @BindView(R.id.tv_device_num)
    TextView tvDeviceNum;
    @BindView(R.id.tv_network_method)
    TextView tvNetworkMethod;
    @BindView(R.id.tv_wireless_name)
    TextView tvWirelessName;
    @BindView(R.id.ll_devices_managment)
    LinearLayout llDevicesManagment;
    @BindView(R.id.ll_network_managment)
    LinearLayout llNetworkManagment;
    @BindView(R.id.ll_wireless_setting)
    LinearLayout llWirelessSetting;
    private Unbinder unbinder;

    private SiUtil mSiUtil;
    private static LocalApi localApi;

    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private DevicesManagementFragment devicesManagementFragment;
    private NetworkManagementFragment netwrokManagementFragment;
    private WirelessSettingFragment wirelessSettingFragment;

    public static CommonSettingFragment newInstance() {
        CommonSettingFragment commonSettingFragment = new CommonSettingFragment();
        Bundle args = new Bundle();
        commonSettingFragment.setArguments(args);
        return commonSettingFragment;
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
        View view = inflater.inflate(R.layout.fragment_common_setting, container, false);
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
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        mSiUtil = new SiUtil(getActivity());
        localApi = mSiUtil.localApiInit();

        devicesManagementFragment = DevicesManagementFragment.newInstance(localApi);
        netwrokManagementFragment = NetworkManagementFragment.newInstance(localApi);
        wirelessSettingFragment = WirelessSettingFragment.newInstance(localApi);
        fragmentList.add(devicesManagementFragment);
        fragmentList.add(netwrokManagementFragment);
        fragmentList.add(wirelessSettingFragment);

        fragmentManager = getFragmentManager();
    }

    private void initView() {
        //初始化界面　首页展示设备管理界面
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout_common_setting, devicesManagementFragment)
                .commit();
        currentFragment = devicesManagementFragment;

        llDevicesManagment.setOnFocusChangeListener((v, hasFocus) -> {
            switchFragment(currentFragment, devicesManagementFragment);
            currentFragment = devicesManagementFragment;

        });
        llNetworkManagment.setOnFocusChangeListener((v, hasFocus) -> {
            switchFragment(currentFragment, netwrokManagementFragment);
            currentFragment = netwrokManagementFragment;
        });
        llWirelessSetting.setOnFocusChangeListener((v, hasFocus) -> {
            switchFragment(currentFragment, wirelessSettingFragment);
            currentFragment = wirelessSettingFragment;
        });
    }

    private void initData() {

    }

    @OnClick({R.id.ll_devices_managment, R.id.ll_network_managment, R.id.ll_wireless_setting})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_devices_managment:
                switchFragment(currentFragment, devicesManagementFragment);
                currentFragment = devicesManagementFragment;
                break;
            case R.id.ll_network_managment:
                switchFragment(currentFragment, netwrokManagementFragment);
                currentFragment = netwrokManagementFragment;
                break;
            case R.id.ll_wireless_setting:
                switchFragment(currentFragment, wirelessSettingFragment);
                currentFragment = wirelessSettingFragment;
                break;
        }
    }

    /**
     * 切换Fragment
     *
     * @param fromFragment：需要隐藏的Fragment
     * @param toFragment：需要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (fromFragment != toFragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_common_setting, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

}
