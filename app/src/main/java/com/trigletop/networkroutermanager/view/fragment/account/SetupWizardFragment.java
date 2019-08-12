package com.trigletop.networkroutermanager.view.fragment.account;

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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortIPAddressAutoFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortPPOEFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortStaticIPAddressFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class SetupWizardFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static SetupWizardFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        SetupWizardFragment setupWizardFragment = new SetupWizardFragment();
        Bundle args = new Bundle();
        setupWizardFragment.setArguments(args);
        return setupWizardFragment;
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
        View view = inflater.inflate(R.layout.fragment_setupwizard, container, false);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        viewPager.setAdapter(new DevicesManagementAdapter(
                getChildFragmentManager(),
                mLocalApi,
                getString(R.string.IP_address_auto),
                getString(R.string.static_ip),
                getString(R.string.PPOE)
        ));
        tab.setupWithViewPager(viewPager);
    }

    private void initView() {

    }

    private void initData() {

    }

    private class DevicesManagementAdapter extends FragmentPagerAdapter {

        private String[] mTitles;
        private LocalApi mLocalApi;
        private List<androidx.fragment.app.Fragment> fragmentList = new ArrayList<>();

        DevicesManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
            super(fm);
            mLocalApi = localApi;
            mTitles = titles;
            fragmentList.add(WANPortIPAddressAutoFragment.newInstance(mLocalApi));
            fragmentList.add(WANPortStaticIPAddressFragment.newInstance(mLocalApi));
            fragmentList.add(WANPortPPOEFragment.newInstance(mLocalApi));
        }

        @NonNull
        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
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
