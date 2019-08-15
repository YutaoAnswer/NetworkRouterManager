package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

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

public class WANPortStaticIPAddressFragment extends Fragment {

    private static final String TAG = WANPortPPOEFragment.class.getSimpleName();

    private static LocalApi mLocalApi;
    @BindView(R.id.et_ip_address)
    TextView etIpAddress;
    @BindView(R.id.et_subnet_mask)
    TextView etSubnetMask;
    @BindView(R.id.et_gateway)
    TextView etGateway;
    @BindView(R.id.et_preferred_dns_server)
    TextView etPreferredDnsServer;
    @BindView(R.id.et_spare_dns_server)
    TextView etSpareDnsServer;
    private Unbinder unbinder;

    public static WANPortStaticIPAddressFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortStaticIPAddressFragment wanPortStaticIPAddressFragment = new WANPortStaticIPAddressFragment();
        Bundle args = new Bundle();
        wanPortStaticIPAddressFragment.setArguments(args);
        return wanPortStaticIPAddressFragment;
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
        View view = inflater.inflate(R.layout.fragment_wanportstaticipaddress, container, false);
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

    private void init() {

    }

    private void initView() {

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

    @OnClick(R.id.btn_address_auto_save)
    public void onViewClicked() {

    }
}
