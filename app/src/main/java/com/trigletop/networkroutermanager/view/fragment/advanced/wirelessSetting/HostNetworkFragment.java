package com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiAdvanceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiAdvanceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;

public class HostNetworkFragment extends Fragment {

    private static final String TAG = HostNetworkFragment.class.getSimpleName();

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.et_wireless_name)
    EditText etWirelessName;
    @BindView(R.id.et_wireless_psw)
    EditText etWirelessPsw;
    @BindView(R.id.btn_channel)
    Button btnChannel;
    @BindView(R.id.btn_mode)
    Button btnMode;
    @BindView(R.id.btn_bandwidth)
    Button btnBandwidth;
    @BindView(R.id.btn_signal_strength)
    Button btnSignalStrength;
    @BindView(R.id.btn_static_address_save)
    Button btnStaticAddressSave;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;
    private FragmentManager childFragmentManager;

    public static HostNetworkFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        HostNetworkFragment hostNetworkFragment = new HostNetworkFragment();
        Bundle args = new Bundle();
        hostNetworkFragment.setArguments(args);
        return hostNetworkFragment;
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
        View view = inflater.inflate(R.layout.fragment_host, container, false);
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
        childFragmentManager = getChildFragmentManager();
    }

    private void initView() {
        tab.addTab(tab.newTab().setText(getString(R.string.wireless_one)));
        tab.addTab(tab.newTab().setText(getString(R.string.wireless_two)));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                GetWiFiDetailParam getWiFiDetailParam = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
//                GetWiFiAdvanceParam getWiFiAdvanceParam = new GetWiFiAdvanceParam(LocalApi.DEFAULT_APP_API_VERSION);
                switch (tab.getPosition()) {
                    case 0:
                        Single<GetWiFiDetailRet> getWiFiDetailRetSingle
                                = mLocalApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
                        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
//                                etWirelessName.setText(getWiFiDetailRet.getInfo().get(0).getSsid());
//                                etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(0).getPassword());
//                                btnChannel.setText(getWiFiDetailRet.getInfo().get(0).getChannel());
//                                btnBandwidth.setText(getWiFiDetailRet.getInfo().get(0).getBand());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });

                        mLocalApi.executeApiWithSingleResponse(new GetWiFiAdvanceParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiAdvanceRet.class)
                                .observeOn(Schedulers.trampoline())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<GetWiFiAdvanceRet>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(GetWiFiAdvanceRet getWiFiAdvanceRet) {
//                                        btnMode.setText(getWiFiAdvanceRet.getInfo().get(0).signalmode);
//                                        btnSignalStrength.setText(getWiFiAdvanceRet.getInfo().get(position[0]).);
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                        break;
                    case 1:
//                        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
//                                .observeOn(Schedulers.trampoline())
//                                .subscribeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//
//                                    }
//
//                                    @Override
//                                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
//                                        for (int i = 0; i < getWiFiDetailRet.getInfo().size(); i++) {
//                                            if ("2.4".equals(getWiFiDetailRet.getInfo().get(i).band)) {
//                                                etWirelessName.setText(getWiFiDetailRet.getInfo().get(i).getSsid());
//                                                etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(i).getPassword());
//                                                btnChannel.setText(getWiFiDetailRet.getInfo().get(i).getChannel());
//                                                btnBandwidth.setText(getWiFiDetailRet.getInfo().get(i).getBand());
//                                            }
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//                                });
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initData() {
        Single<GetWiFiDetailRet> getWiFiDetailRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                etWirelessName.setText(getWiFiDetailRet.getInfo().get(0).getSsid());
                etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(0).getPassword());
//                btnChannel.setText(getWiFiDetailRet.getInfo().get(0).getChannel());
//                btnBandwidth.setText(getWiFiDetailRet.getInfo().get(0).getBand());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @OnClick({R.id.btn_channel, R.id.btn_mode, R.id.btn_bandwidth, R.id.btn_signal_strength, R.id.btn_static_address_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_channel:
                // TODO: 19-8-12 弹窗

                break;
            case R.id.btn_mode:
                // TODO: 19-8-12 弹窗

                break;
            case R.id.btn_bandwidth:
                // TODO: 19-8-12 弹窗

                break;
            case R.id.btn_signal_strength:

                break;
            case R.id.btn_static_address_save:
                save();
                break;
        }
    }

    private void save() {
        // TODO: 19-8-12 2.4G　  5G
        SetWiFiDetailParam setWiFiDetailParam = new SetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
        WifiParam siWiFiSetParam5 = new WifiParam();
        List<WifiParam> params = new ArrayList<>();
        WifiParam siWiFiSetParam24 = new WifiParam();
        siWiFiSetParam24.oldssid = "siflower-2.4G";
        siWiFiSetParam24.password = "12345678";
        siWiFiSetParam24.enable = 1;
        siWiFiSetParam24.encryption = "psk2+ccmp";
        siWiFiSetParam24.newssid = "siwifi-2.4G";
        siWiFiSetParam24.channel = 13;

        siWiFiSetParam5.oldssid = "siflower-5G";
        siWiFiSetParam5.password = "12345678";
        siWiFiSetParam5.enable = 1;
        siWiFiSetParam5.encryption = "psk2+ccmp";
        siWiFiSetParam5.newssid = "siwifi-5G";
        siWiFiSetParam5.channel = 161;
        params.add(siWiFiSetParam5);
        setWiFiDetailParam.setWifiParamsList(params);

        mLocalApi.executeApiWithSingleResponse(setWiFiDetailParam, SetWiFiDetailRet.class)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

//    /**
//     * 切换Fragment
//     *
//     * @param fromFragment：需要隐藏的Fragment
//     * @param toFragment：需要显示的Fragment
//     */
//    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
//        if (fromFragment != toFragment) {
//            FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
//            if (!toFragment.isAdded()) {
//                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_host, toFragment).commit();
//            } else {
//                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
//            }
//        }
//    }

}
