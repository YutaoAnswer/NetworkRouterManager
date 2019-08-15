package com.trigletop.networkroutermanager.view.fragment.common.networkManagment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWanTypeRet;

public class IPAddressAutoFragment extends Fragment {

    private static final String TAG = IPAddressAutoFragment.class.getSimpleName();

    @BindView(R.id.tv_ip_address)
    TextView tvIpAddress;
    @BindView(R.id.tv_subnet_mask)
    TextView tvSubnetMask;
    @BindView(R.id.tv_gateway)
    TextView tvGateway;
    @BindView(R.id.tv_preferred_dns_server)
    TextView tvPreferredDnsServer;
    @BindView(R.id.tv_spare_dns_server)
    TextView tvSpareDnsServer;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static IPAddressAutoFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        IPAddressAutoFragment ipAddressAutoFragment = new IPAddressAutoFragment();
        Bundle args = new Bundle();
        ipAddressAutoFragment.setArguments(args);
        return ipAddressAutoFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ipaddressauto, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        intiView();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {

    }

    private void intiView() {

    }

    private void initData() {
        Single<GetWanTypeRet> getWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION), GetWanTypeRet.class);
        getWanTypeRetSingle.subscribe(new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "IPAddressAuto onSubscribe: ");

            }

            @Override
            public void onSuccess(GetWanTypeRet getWanTypeRet) {
                Log.d(TAG, "onSuccess: ");
                Log.d(TAG, "IPAddressAuto onSuccess: " + getWanTypeRet.toString());
                tvIpAddress.setText(getWanTypeRet.getIp());
                tvGateway.setText(getWanTypeRet.getGateway());
                tvSubnetMask.setText(getWanTypeRet.getGateway());
                tvPreferredDnsServer.setText(getWanTypeRet.getDns1());
                tvSpareDnsServer.setText(getWanTypeRet.getDns2());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                // TODO: 19-8-9 取取数据失败，请重试

            }
        });
    }

    @OnClick(R.id.btn_address_auto_save)
    public void onViewClicked() {
        // TODO: 19-8-6 保存更改
        String gateWay = tvGateway.getText().toString();
        String ipAddress = tvIpAddress.getText().toString();
        String preferredDnsServer = tvPreferredDnsServer.getText().toString();
        String spareDnsServer = tvSpareDnsServer.getText().toString();
        String subnetMask = tvSubnetMask.getText().toString();
        if (!gateWay.isEmpty() && !ipAddress.isEmpty() && !preferredDnsServer.isEmpty() && !spareDnsServer.isEmpty() && !subnetMask.isEmpty()) {
            SetWanTypeParam setWanTypeParam = new SetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION);
            setWanTypeParam.setAddress(ipAddress);
            setWanTypeParam.setMask(subnetMask);
            setWanTypeParam.setGateway(gateWay);
            setWanTypeParam.setDns1(preferredDnsServer);
            setWanTypeParam.setDns2(spareDnsServer);
            Single<SetWanTypeRet> setWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(setWanTypeParam, SetWanTypeRet.class);
            setWanTypeRetSingle.subscribe(new SingleObserver<SetWanTypeRet>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(SetWanTypeRet setWanTypeRet) {
                    // TODO: 19-8-6 弹窗
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                            .withTitleColor(R.color.cyan)
                            .withMessage(getActivity().getString(R.string.setting_successful))
                            .withMessageColor("#FFFFFFFF")
                            .withEffect(Effectstype.Fadein)
                            .withDuration(700)
                            .show();
                }

                @Override
                public void onError(Throwable e) {
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                            .withTitleColor(R.color.cyan)
                            .withMessage(getActivity().getString(R.string.setting_successful))
                            .withMessageColor("#FFFFFFFF")
                            .withEffect(Effectstype.Fadein)
                            .withDuration(700)
                            .show();
                }
            });
        }
    }
}
