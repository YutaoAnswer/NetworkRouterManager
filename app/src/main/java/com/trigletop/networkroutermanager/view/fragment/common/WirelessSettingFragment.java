package com.trigletop.networkroutermanager.view.fragment.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType.WirelessTypeOneFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;

public class WirelessSettingFragment extends Fragment {

    private static final String TAG = WirelessTypeOneFragment.class.getSimpleName();

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.cb_no_psw)
    CheckBox cbNoPsw;
    @BindView(R.id.et_wireless_name)
    EditText etWirelessName;
    @BindView(R.id.et_wireless_psw)
    EditText etWirelessPsw;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static WirelessSettingFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WirelessSettingFragment wirelessSettingFragment = new WirelessSettingFragment();
        Bundle args = new Bundle();
        wirelessSettingFragment.setArguments(args);
        return wirelessSettingFragment;
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
        View view = inflater.inflate(R.layout.fragment_wireless_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        intData();
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

    private void init() {

    }

    private void initView() {
        Single<GetWiFiDetailRet> getWiFiDetailRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION), GetWiFiDetailRet.class);
        getWiFiDetailRetSingle.subscribe(new SingleObserver<GetWiFiDetailRet>() {
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

    private void intData() {

    }

    @OnClick(R.id.btn_wireless_setting_save)
    void onViewClicked() {
        String wirelesspas = etWirelessPsw.getText().toString();
        String wirelessname = etWirelessName.getText().toString();
        if (!wirelessname.isEmpty() && !wirelesspas.isEmpty()) {
            SetWiFiDetailParam setWiFiDetailParam = new SetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
            WifiParam wifiParam = setWiFiDetailParam.getWifiParamsList().get(0);
            // TODO: 19-8-9 验证设置是否成功
            wifiParam.password = wirelesspas;
            wifiParam.oldssid = wirelessname;
            Single<SetWiFiDetailRet> setWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(setWiFiDetailParam, SetWiFiDetailRet.class);
            setWanTypeRetSingle.subscribe(new SingleObserver<SetWiFiDetailRet>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {
                    Log.d(TAG, "onSuccess: ");
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                            .withTitleColor(R.color.cyan)
                            .withMessage(getActivity().getString(R.string.successful_save))
                            .withMessageColor("#FFFFFFFF")
                            .withEffect(Effectstype.Fadein)
                            .withDuration(700)
                            .show();
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } else {
            NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
            dialogBuilder
                    .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                    .withTitleColor(R.color.cyan)
                    .withMessage(getActivity().getString(R.string.unsuccessful_save))
                    .withMessageColor("#FFFFFFFF")
                    .withEffect(Effectstype.Fadein)
                    .withDuration(700)
                    .show();
        }
    }

}
