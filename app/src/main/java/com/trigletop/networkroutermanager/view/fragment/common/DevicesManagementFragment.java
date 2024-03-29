package com.trigletop.networkroutermanager.view.fragment.common;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.common.devicesManagement.ConnectedFragment;
import com.trigletop.networkroutermanager.view.fragment.common.devicesManagement.ForbiddenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class DevicesManagementFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static DevicesManagementFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        DevicesManagementFragment devicesManagementFragment = new DevicesManagementFragment();
        Bundle args = new Bundle();
        devicesManagementFragment.setArguments(args);
        return devicesManagementFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devices_managment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        viewPager.setAdapter(new DevicesManagementAdapter(
                getChildFragmentManager(),
                mLocalApi,
                getString(R.string.connected),
                getString(R.string.forbidden)));
        tab.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class DevicesManagementAdapter extends FragmentPagerAdapter {
        private String[] mTitles;
        private LocalApi mLocalApi;

        DevicesManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
            super(fm);
            mLocalApi = localApi;
            mTitles = titles;
//            List<Fragment> fragmentList = new ArrayList<>();
//            fragmentList.add(ConnectedFragment.newInstance(mLocalApi));
//            fragmentList.add(ForbiddenFragment.newInstance());
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ConnectedFragment.newInstance(mLocalApi);
                case 1:
                    return ForbiddenFragment.newInstance();
                default:
                    break;
            }
            return null;
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
