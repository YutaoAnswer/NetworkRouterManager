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
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trigletop.networkroutermanager.R;

import java.util.ArrayList;
import java.util.List;

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
    //    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @BindView(R.id.frameLayout_wireless_setting)
    LinearLayout frameLayoutWirelessSetting;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;
    private WANPortIPAddressAutoFragment wanPortIPAddressAutoFragment;
    private WANPortStaticIPAddressFragment wanPortStaticIPAddressFragment;
    private WANPortPPOEFragment wanPortPPOEFragment;
    private Fragment currentFragment;
    private FragmentManager childFragmentManager;

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
        childFragmentManager = getChildFragmentManager();
        wanPortIPAddressAutoFragment = WANPortIPAddressAutoFragment.newInstance(mLocalApi);
        wanPortStaticIPAddressFragment = WANPortStaticIPAddressFragment.newInstance(mLocalApi);
        wanPortPPOEFragment = WANPortPPOEFragment.newInstance(mLocalApi);
    }

    private void initView() {
        tab.addTab(tab.newTab().setText(getString(R.string.IP_address_auto)));
        tab.addTab(tab.newTab().setText(getString(R.string.static_ip)));
        tab.addTab(tab.newTab().setText(getString(R.string.PPOE)));

        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout_wanport_setting, wanPortIPAddressAutoFragment)
                .commit();
        currentFragment = wanPortIPAddressAutoFragment;

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        switchFragment(currentFragment, wanPortIPAddressAutoFragment);
                        currentFragment = wanPortIPAddressAutoFragment;
                        break;
                    case 1:
                        switchFragment(currentFragment, wanPortStaticIPAddressFragment);
                        currentFragment = wanPortStaticIPAddressFragment;
                        break;
                    case 2:
                        switchFragment(currentFragment, wanPortPPOEFragment);
                        currentFragment = wanPortPPOEFragment;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

//    private class WANPortSettingManagementAdapter extends FragmentPagerAdapter {
//
//        private String[] mTitles;
//        private LocalApi mLocalApi;
//        private List<Fragment> fragmentList = new ArrayList<>();
//
//        WANPortSettingManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
//            super(fm);
//            mLocalApi = localApi;
//            mTitles = titles;
//            fragmentList.add(WANPortIPAddressAutoFragment.newInstance(mLocalApi));
//            fragmentList.add(WANPortStaticIPAddressFragment.newInstance(mLocalApi));
//            fragmentList.add(WANPortPPOEFragment.newInstance(mLocalApi));
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mTitles.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }
//
//    }

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
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_wanport_setting, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

}
