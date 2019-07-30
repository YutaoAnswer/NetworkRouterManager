package com.trigletop.networkroutermanager.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.trigletop.networkroutermanager.Bean.Device;
import com.trigletop.networkroutermanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DeviceDetailFragment extends Fragment {

    @BindView(R.id.frameLayout_deviceDetail)
    FrameLayout frameLayoutDeviceDetail;
    Unbinder unbinder;

    public static DeviceDetailFragment newInstance(Device device) {
        DeviceDetailFragment deviceDetailFragment = new DeviceDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("device", device);
        deviceDetailFragment.setArguments(args);
        return deviceDetailFragment;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devicedetail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        frameLayoutDeviceDetail.requestFocus();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
