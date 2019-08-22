package com.trigletop.networkroutermanager.view.fragment.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.common.networkManagment.IPAddressAutoFragment;
import com.trigletop.networkroutermanager.view.fragment.common.networkManagment.PPOEFragment;
import com.trigletop.networkroutermanager.view.fragment.common.networkManagment.StaticIPAddressFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class NetworkManagementFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;
    private FragmentManager childFragmentManager;
    private Fragment currentFragment;
    private PPOEFragment ppoeFragment;
    private StaticIPAddressFragment staticIPAddressFragment;
    private IPAddressAutoFragment ipAddressAutoFragment;
    private List<Fragment> fragmentArrayList = new ArrayList<>();

    public static NetworkManagementFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        NetworkManagementFragment networkManagementFragment = new NetworkManagementFragment();
        Bundle args = new Bundle();
        networkManagementFragment.setArguments(args);
        return networkManagementFragment;
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
        View view = inflater.inflate(R.layout.fragment_network_managment, container, false);
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
        childFragmentManager = getChildFragmentManager();
        ppoeFragment = PPOEFragment.newInstance(mLocalApi);
        staticIPAddressFragment = StaticIPAddressFragment.newInstance(mLocalApi);
        ipAddressAutoFragment = IPAddressAutoFragment.newInstance(mLocalApi);
        currentFragment = ppoeFragment;
        fragmentArrayList.add(ppoeFragment);
        fragmentArrayList.add(staticIPAddressFragment);
        fragmentArrayList.add(ipAddressAutoFragment);
    }

    private void initView() {
        viewPager.setAdapter(new NetworkManagmentAdapter(
                getChildFragmentManager(),
                mLocalApi,
                getString(R.string.PPOE),
                getString(R.string.static_ip),
                getString(R.string.IP_address_auto)));
        tab.setupWithViewPager(viewPager);
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

    private class NetworkManagmentAdapter extends FragmentPagerAdapter {

        private String[] mTitles;
        private LocalApi mLocalApi;
        private List<Fragment> fragmentList;

        NetworkManagmentAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
            super(fm);
            mLocalApi = localApi;
            mTitles = titles;
            fragmentList = fragmentArrayList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

    }

    /**
     * 切换Fragment
     *
     * @param fromFragment：需要隐藏的Fragment
     * @param toFragment：需要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (fromFragment != toFragment) {
            FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_common_setting, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }
}
