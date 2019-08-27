package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;

public class WANPortIPAddressAutoFragment extends Fragment {

    private static final String TAG = WANPortIPAddressAutoFragment.class.getSimpleName();

    private static LocalApi mLocalApi;

    @BindView(R.id.tv_ip_address)
    TextView tvIpAddress;
    @BindView(R.id.tv_subnet_mask)
    TextView tvSubnetMask;
    @BindView(R.id.tv_gateway)
    TextView tvGateway;
    @BindView(R.id.tv_dns_server)
    TextView tvDnsServer;
    @BindView(R.id.et_data_pack)
    EditText etDataPack;
    @BindView(R.id.et_preferred_dns_server)
    EditText etPreferredDnsServer;
    @BindView(R.id.et_spare_dns_server)
    EditText etSpareDnsServer;
    @BindView(R.id.et_host_name)
    EditText etHostName;
    @BindView(R.id.ll_wan_port_setting)
    LinearLayout llWanPortSetting;
    @BindView(R.id.btn_save)
    Button btnSave;
    private Unbinder unbinder;

    public static WANPortIPAddressAutoFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortIPAddressAutoFragment wanPortIPAddressAutoFragment = new WANPortIPAddressAutoFragment();
        Bundle args = new Bundle();
        wanPortIPAddressAutoFragment.setArguments(args);
        return wanPortIPAddressAutoFragment;
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
        View view = inflater.inflate(R.layout.fragment_wanportipaddressauto, container, false);
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
                tvIpAddress.setText(getWanTypeRet.getIp());
                tvSubnetMask.setText(getWanTypeRet.getMask());
                tvGateway.setText(getWanTypeRet.getGateway());
                tvDnsServer.setText(getWanTypeRet.getDns1());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                // TODO: 19-8-9 取取数据失败，请重试

            }
        });

    }

    private void initView() {

    }

    private void init() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_update, R.id.btn_release, R.id.btn_wan_port_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:

                break;
            case R.id.btn_release:

                break;
            case R.id.btn_wan_port_setting:

                break;
        }
    }
}
