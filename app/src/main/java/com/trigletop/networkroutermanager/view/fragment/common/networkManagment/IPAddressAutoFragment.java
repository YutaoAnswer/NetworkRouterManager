package com.trigletop.networkroutermanager.view.fragment.common.networkManagment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

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

    private SiUtil siUtil;

    private static LocalApi mLocalApi;

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == Data.handler_message_setWanType_PPOE) {
//                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
//                dialogBuilder
//                        .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.upload_download_limit))
//                        .withDuration(700)
//                        .show();
//            }
//        }
//    };

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
        siUtil = new SiUtil(getActivity());
    }

    private void intiView() {

    }

    private void initData() {
//        siUtil.getWanTypeRet(mLocalApi);

        Single<GetWanTypeRet> getWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION), GetWanTypeRet.class);
        getWanTypeRetSingle.subscribe(new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWanTypeRet getStokLocalRet) {
                tvIpAddress.setText(getStokLocalRet.getIp());
                tvGateway.setText(getStokLocalRet.getGateway());
                tvSubnetMask.setText(getStokLocalRet.getGateway());
                tvPreferredDnsServer.setText(getStokLocalRet.getDns1());
                tvSpareDnsServer.setText(getStokLocalRet.getDns2());
            }

            @Override
            public void onError(Throwable e) {

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

            }

            @Override
            public void onError(Throwable e) {
            }
        });

    }
}
