package com.trigletop.networkroutermanager.view.fragment.main;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.NormalAdapter;
import com.trigletop.networkroutermanager.utils.SiUtil;
import com.trigletop.networkroutermanager.view.fragment.ExtranetSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.FloodControlNetworkFragment;
import com.trigletop.networkroutermanager.view.fragment.NetworkDetectionFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.UpdateFragment;
import com.trigletop.networkroutermanager.view.fragment.WIFILeaseFragment;
import com.trigletop.networkroutermanager.view.fragment.WIFISettingFragment;

import java.util.ArrayList;
import java.util.List;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class AdvancedSettingFragment extends Fragment {

    private static final String TAG = AdvancedSettingFragment.class.getSimpleName();

    @BindView(R.id.rcy_setting)
    TvRecyclerView rcySetting;
    Unbinder unbinder;

    private SiUtil siUtil;
    private LocalApi localApi;

    private List<Fragment> fragmentList = new ArrayList<>();

    public static AdvancedSettingFragment newInstance() {
        AdvancedSettingFragment advancedSettingFragment = new AdvancedSettingFragment();
        Bundle args = new Bundle();
        advancedSettingFragment.setArguments(args);
        return advancedSettingFragment;
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
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        siUtil = new SiUtil(getActivity());
        localApi = siUtil.localApiInit();

        ExtranetSettingFragment extranetSettingFragment = new ExtranetSettingFragment();//外网设置
        WIFILeaseFragment wifiLeaseFragment = new WIFILeaseFragment();//WIFI租赁
        WIFISettingFragment wifiSettingFragment = new WIFISettingFragment();//WIFI设置
        FloodControlNetworkFragment floodControlNetworkFragment = new FloodControlNetworkFragment();//防蹭网
        NetworkDetectionFragment networkDetectionFragment = new NetworkDetectionFragment();//网络检测
        UpdateFragment updateFragment = new UpdateFragment();//固件升级
        fragmentList.add(extranetSettingFragment);
        fragmentList.add(wifiLeaseFragment);
        fragmentList.add(wifiSettingFragment);
        fragmentList.add(floodControlNetworkFragment);
        fragmentList.add(networkDetectionFragment);
        fragmentList.add(updateFragment);
    }

    private void initView() {
        //RecyclerView
        TvRecyclerView.openDEBUG();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcySetting.setLayoutManager(manager);

        int itemSpace = getResources().getDimensionPixelSize(R.dimen.recyclerView_item_space);
        rcySetting.addItemDecoration(new SpaceItemDecoration(itemSpace));
        NormalAdapter mAdapter = new NormalAdapter(getActivity(), TAG);
        rcySetting.setAdapter(mAdapter);

        rcySetting.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onItemViewClick(View view, int position) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null).add(R.id.frameLayout, fragmentList.get(position)).replace(R.id.frameLayout, fragmentList.get(position)).commit();
            }

            @Override
            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {

            }
        });
    }

    private void initData() {

    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = space;
            outRect.top = space;
        }
    }

}
