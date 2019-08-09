package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.common.DevicesManagementFragment;
import com.trigletop.networkroutermanager.view.fragment.common.devicesManagement.ConnectedFragment;
import com.trigletop.networkroutermanager.view.fragment.common.devicesManagement.ForbiddenFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;

public class WANPortSettingFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.frameLayout_wireless_setting)
    LinearLayout frameLayoutWirelessSetting;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static WANPortSettingFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortSettingFragment extranetSettingFragment = new WANPortSettingFragment();
        Bundle args = new Bundle();
        extranetSettingFragment.setArguments(args);
        return extranetSettingFragment;
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
        View view = inflater.inflate(R.layout.fragment_wanport_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
        initData();
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {

    }

    private void initView() {
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        viewPager.setAdapter(new DevicesManagementAdapter(
                Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                mLocalApi,
                getString(R.string.IP_address_auto),
                getString(R.string.static_ip),
                getString(R.string.PPOE)
        ));
        tab.setupWithViewPager(viewPager);
    }

    private void initData() {
        Single<GetWanTypeRet> getWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION), GetWanTypeRet.class);
        getWanTypeRetSingle.subscribe(new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWanTypeRet getWanTypeRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private class DevicesManagementAdapter extends FragmentPagerAdapter {

        private String[] mTitles;
        private LocalApi mLocalApi;
        private List<Fragment> fragmentList = new ArrayList<>();

        DevicesManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
            super(fm);
            mLocalApi = localApi;
            mTitles = titles;
            // TODO: 19-8-8 不显示
            fragmentList.add(WANPortIPAddressAutoFragment.newInstance(mLocalApi));
            fragmentList.add(WANPortStaticIPAddressFragment.newInstance(mLocalApi));
            fragmentList.add(WANPortPPOEFragment.newInstance(mLocalApi));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

    }

}
