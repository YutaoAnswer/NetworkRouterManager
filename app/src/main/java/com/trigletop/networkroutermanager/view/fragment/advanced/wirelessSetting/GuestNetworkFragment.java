package com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType.WirelessTypeOneFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType.WirelessTypeTwoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;

public class GuestNetworkFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.et_wireless_name)
    EditText etWirelessName;
    @BindView(R.id.et_wireless_psw)
    EditText etWirelessPsw;
    @BindView(R.id.et_guest_network_upload_speed)
    EditText etGuestNetworkUploadSpeed;
    @BindView(R.id.et_guest_network_download_speed)
    EditText etGuestNetworkDownloadSpeed;
    @BindView(R.id.btn_intranet_resource)
    Button btnIntranetResource;
    @BindView(R.id.btn_guest_network_opening_time)
    Button btnGuestNetworkOpeningTime;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;
    private WirelessTypeOneFragment wirelessTypeOneFragment;
    private WirelessTypeTwoFragment wirelessTypeTwoFragment;
    private Fragment currentFragment;
    private FragmentManager childFragmentManager;

    public static GuestNetworkFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        GuestNetworkFragment guestFragment = new GuestNetworkFragment();
        Bundle args = new Bundle();
        guestFragment.setArguments(args);
        return guestFragment;
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
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
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
                GetWiFiDetailParam getWiFiDetailParam = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
                switch (tab.getPosition()) {
                    case 0:
                        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                                .observeOn(Schedulers.trampoline())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                                        etWirelessName.setText(getWiFiDetailRet.getInfo().get(0).getSsid());
                                        etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(0).getPassword());
                                        // TODO: 19-8-14 button按钮切换

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                        break;
                    case 1:
                        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                                .observeOn(Schedulers.trampoline())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                                        // TODO: 2019-09-03 获取数据
                                        etWirelessName.setText(getWiFiDetailRet.getInfo().get(2).getSsid());
                                        etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(2).getPassword());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
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
        GetWiFiDetailParam getWiFiDetailParam = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {
                        etWirelessName.setText(getWiFiDetailRet.getInfo().get(0).getSsid());
                        etWirelessPsw.setText(getWiFiDetailRet.getInfo().get(0).getPassword());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @OnClick({R.id.btn_intranet_resource, R.id.btn_guest_network_opening_time, R.id.btn_static_address_save})
    public void onViewClicked(View view) {
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
        switch (view.getId()) {
            case R.id.btn_intranet_resource:
                dialogBuilder
                        .withTitle("是否允许方可访问内网资源")
                        .withDuration(700)
                        .withDialogColor("#0096a6")
                        .withButton1Text("是")
                        .withButton2Text("否")
                        .setButton1Click(v -> {
                            btnIntranetResource.setText("是");
                            dialogBuilder.dismiss();
                        })
                        .setButton2Click(v -> {
                            btnIntranetResource.setText("否");
                            dialogBuilder.dismiss();
                        })
                        .withEffect(Effectstype.SlideBottom)
                        .isCancelableOnTouchOutside(true)
                        .show();
                break;
            case R.id.btn_guest_network_opening_time:
                dialogBuilder
                        .withTitle("是否设置访客网络开放时间")
                        .withDuration(700)
                        .withDialogColor("#0096a6")
                        .withButton1Text("是")
                        .withButton2Text("否")
                        .setButton1Click(v -> {
                            btnGuestNetworkOpeningTime.setText("是");
                            dialogBuilder.dismiss();
                        })
                        .setButton2Click(v -> {
                            btnGuestNetworkOpeningTime.setText("否");
                            dialogBuilder.dismiss();
                        })
                        .withEffect(Effectstype.SlideBottom)
                        .isCancelableOnTouchOutside(true)
                        .show();
                break;
            case R.id.btn_static_address_save:
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
                                Toast.makeText(getContext(), "设置成功！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
                break;
        }
    }

//    private class DevicesManagementAdapter extends FragmentPagerAdapter {
//
//        private String[] mTitles;
//        private LocalApi mLocalApi;
//        private List<Fragment> fragmentList = new ArrayList<>();
//
//        DevicesManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
//            super(fm);
//            mLocalApi = localApi;
//            mTitles = titles;
//            fragmentList.add(WirelessTypeOneFragment.newInstance(mLocalApi));
//            fragmentList.add(WirelessTypeTwoFragment.newInstance(mLocalApi));
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mTitles.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//    }

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
//                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_guest, toFragment).commit();
//            } else {
//                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
//            }
//        }
//    }
}
