package com.trigletop.networkroutermanager.view.fragment.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.DevicesAdapter;
import com.trigletop.networkroutermanager.utils.SiUtil;
import com.trigletop.networkroutermanager.view.fragment.common.DevicesManagementFragment;
import com.trigletop.networkroutermanager.view.fragment.common.NetworkManagementFragment;
import com.trigletop.networkroutermanager.view.fragment.common.WirelessSettingFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;


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
    @BindView(R.id.iv_devices_managment)
    ImageView ivDevicesManagment;
    @BindView(R.id.iv_network_managment)
    ImageView ivNetworkManagment;
    @BindView(R.id.iv_wireless_setting)
    ImageView ivWirelessSetting;
    @BindView(R.id.frameLayout_common_setting)
    FrameLayout frameLayoutCommonSetting;
    private Unbinder unbinder;

    private SiUtil mSiUtil;
    private static LocalApi localApi;

    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private DevicesManagementFragment devicesManagementFragment;
    private NetworkManagementFragment netwrokManagementFragment;
    private WirelessSettingFragment wirelessSettingFragment;

    private List<ImageView> imageViewList = new ArrayList<>();
    private int currentFocusPostition = 0;
    private static LocalApi mLocalApi;

    public static CommonSettingFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
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

        imageViewList.add(ivDevicesManagment);
        imageViewList.add(ivNetworkManagment);
        imageViewList.add(ivWirelessSetting);
    }

    private void initView() {
        //初始化界面　首页展示设备管理界面
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout_common_setting, devicesManagementFragment)
                .commit();
        currentFragment = devicesManagementFragment;
        // TODO: 19-8-19 选中动画
//        ScaleAnimation scaleAnimation_zoom = (ScaleAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.scale_zoom);
//        ScaleAnimation scaleAnimation_narrow = (ScaleAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.scale_narrow);
        llDevicesManagment.setOnFocusChangeListener((v, hasFocus) -> {
//            imageViewList.get(currentFocusPostition).startAnimation(scaleAnimation_narrow);
//            ivDevicesManagment.startAnimation(scaleAnimation_zoom);
//            currentFocusPostition = 0;
            switchFragment(currentFragment, devicesManagementFragment);
            currentFragment = devicesManagementFragment;

        });
        llNetworkManagment.setOnFocusChangeListener((v, hasFocus) -> {
//            imageViewList.get(currentFocusPostition).startAnimation(scaleAnimation_narrow);
//            ivNetworkManagment.startAnimation(scaleAnimation_zoom);
//            currentFocusPostition = 1;
            switchFragment(currentFragment, netwrokManagementFragment);
            currentFragment = netwrokManagementFragment;
        });
        llWirelessSetting.setOnFocusChangeListener((v, hasFocus) -> {
//            imageViewList.get(currentFocusPostition).startAnimation(scaleAnimation_narrow);
//            ivWirelessSetting.startAnimation(scaleAnimation_zoom);
//            currentFocusPostition = 2;
            switchFragment(currentFragment, wirelessSettingFragment);
            currentFragment = wirelessSettingFragment;
        });
    }

    private void initData() {

        //设备管理
        Single<GetDeviceRet> getDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRet.class);
        getDeviceRetSingle.subscribe(new SingleObserver<GetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(GetDeviceRet getDeviceRet) {
                Log.d(TAG, "onSuccess: ");
                tvDeviceNum.setText("已链接设备数量" + getDeviceRet.getList().size());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");

            }
        });

        //上网方式


        //无线设置
        Single<GetWiFiDetailRet> getWiFiDetailRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                tvWirelessName.setText("上网方式" + getWiFiDetailRet.getInfo().get(0).getSsid());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
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
