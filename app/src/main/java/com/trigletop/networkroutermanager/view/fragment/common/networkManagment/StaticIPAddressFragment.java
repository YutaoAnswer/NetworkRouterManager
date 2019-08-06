package com.trigletop.networkroutermanager.view.fragment.common.networkManagment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
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

    private SiUtil siUtil;
    private static LocalApi mLocalApi;
    private String ipAddress;
    private String subNetMask;
    private String gateWay;
    private String preferredDns;
    private String spareDns;

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == Data.handler_message_setWanType_static) {
//                // TODO: 19-8-5 设置上网方式 弹窗提示
//                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
//                dialogBuilder
//                        .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.upload_download_limit))
//                        .withDuration(700)
//                        .show();
//            }
//        }
//    };

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
        View view = inflater.inflate(R.layout.fragment_staticipaddress, container, false);
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {
        siUtil = new SiUtil(getActivity());
    }

    private void initView() {
        ipAddress = etIpAddress.getText().toString();
        subNetMask = etSubnetMask.getText().toString();
        gateWay = etGateway.getText().toString();
        preferredDns = etPreferredDns.getText().toString();
        spareDns = etSpareDns.getText().toString();
    }

    private void initData() {

    }

    @OnClick(R.id.btn_static_address_save)
    public void onViewClicked() {
        if (!ipAddress.isEmpty() && !subNetMask.isEmpty() && !gateWay.isEmpty() && !preferredDns.isEmpty() && !spareDns.isEmpty()) {
            SetWanTypeParam setWanTypeParam = new SetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION);
            setWanTypeParam.setAddress(ipAddress);
            setWanTypeParam.setMask(subNetMask);
            setWanTypeParam.setGateway(gateWay);
            setWanTypeParam.setDns1(preferredDns);
            setWanTypeParam.setDns2(spareDns);
            Single<SetWanTypeRet> setWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(setWanTypeParam, SetWanTypeRet.class);
            setWanTypeRetSingle.subscribe(new SingleObserver<SetWanTypeRet>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(SetWanTypeRet setWanTypeRet) {
//                    Message message = new Message();
//                    message.what = Data.handler_message_setWanType_static;
//                    message.obj = "setDevice";
//                    handler.sendMessage(message);
                    // TODO: 19-8-5 设置上网方式 弹窗提示
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.upload_download_limit))
                            .withDuration(700)
                            .show();
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } else {
            // TODO: 19-8-6 需要修改
            Toast.makeText(getContext(), "错误", Toast.LENGTH_SHORT).show();
        }
    }

}
