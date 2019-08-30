package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

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
    @BindView(R.id.et_data_pack)
    EditText etDataPack;
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
    }

    @OnClick(R.id.btn_address_auto_save)
    public void onViewClicked() {
        String ipAddress = etIpAddress.getText().toString();
        String subnetMask = etSubnetMask.getText().toString();
        String gateway = etGateway.getText().toString();
        String preferredDnsServer = etPreferredDnsServer.getText().toString();
        String spareDnsServer = etSpareDnsServer.getText().toString();

        if (ipAddress.equals(R.string._0_0_0_0)
                && subnetMask.equals(R.string._0_0_0_0)
                && gateway.equals(R.string._0_0_0_0)
                && preferredDnsServer.equals(R.string._0_0_0_0)
                && spareDnsServer.equals(R.string._0_0_0_0)) {
            Toast.makeText(getActivity(), "IP地址非法，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: 19-8-30
        }
    }

}
