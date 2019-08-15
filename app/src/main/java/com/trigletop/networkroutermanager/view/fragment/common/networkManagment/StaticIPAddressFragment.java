package com.trigletop.networkroutermanager.view.fragment.common.networkManagment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class StaticIPAddressFragment extends Fragment {

    private static final String TAG = StaticIPAddressFragment.class.getSimpleName();

    @BindView(R.id.et_ip_address)
    EditText etIpAddress;
    @BindView(R.id.et_subnet_mask)
    EditText etSubnetMask;
    @BindView(R.id.et_gateway)
    EditText etGateway;
    @BindView(R.id.et_preferred_dns)
    EditText etPreferredDns;
    @BindView(R.id.et_spare_dns)
    EditText etSpareDns;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static StaticIPAddressFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        StaticIPAddressFragment staticIPAddressFragment = new StaticIPAddressFragment();
        Bundle args = new Bundle();
        staticIPAddressFragment.setArguments(args);
        return staticIPAddressFragment;
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
        Log.d(TAG, "onCreateView: Static");
        View view = inflater.inflate(R.layout.fragment_staticipaddress, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: Static");
        init();
        initView();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {
    }

    private void initView() {
//        ipAddress = etIpAddress.getText().toString();
//        subNetMask = etSubnetMask.getText().toString();
//        gateWay = etGateway.getText().toString();
//        preferredDns = etPreferredDns.getText().toString();
//        spareDns = etSpareDns.getText().toString();
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
                // TODO: 19-8-14 写处理逻辑

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                // TODO: 19-8-9 取取数据失败，请重试

            }
        });
    }

    @OnClick(R.id.btn_static_address_save)
    public void onViewClicked() {
        String ipAddress = etIpAddress.getText().toString();
        String gateWay = etGateway.getText().toString();
        String subnetMask = etSubnetMask.getText().toString();
        String preferredDNS = etPreferredDns.getText().toString();
        String spareDNS = etSpareDns.getText().toString();

        if (!ipAddress.isEmpty() && !subnetMask.isEmpty() && !gateWay.isEmpty() && !preferredDNS.isEmpty() && !spareDNS.isEmpty()) {
            SetWanTypeParam setWanTypeParam = new SetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION);
            setWanTypeParam.setAddress(ipAddress);
            setWanTypeParam.setMask(subnetMask);
            setWanTypeParam.setGateway(gateWay);
            setWanTypeParam.setDns1(preferredDNS);
            setWanTypeParam.setDns2(spareDNS);
            Single<SetWanTypeRet> setWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(setWanTypeParam, SetWanTypeRet.class);
            setWanTypeRetSingle.subscribe(new SingleObserver<SetWanTypeRet>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(SetWanTypeRet setWanTypeRet) {
                    // TODO: 19-8-5 设置上网方式 弹窗提示
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

                }
            });
        } else {
            // TODO: 19-8-6 需要修改
            NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
            dialogBuilder
                    .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                    .withTitleColor(R.color.cyan)
                    .withMessage(getActivity().getString(R.string.setting_unsuccessful))
                    .withMessageColor("#FFFFFFFF")
                    .withEffect(Effectstype.Fadein)
                    .withDuration(700)
                    .show();
        }
    }

}
