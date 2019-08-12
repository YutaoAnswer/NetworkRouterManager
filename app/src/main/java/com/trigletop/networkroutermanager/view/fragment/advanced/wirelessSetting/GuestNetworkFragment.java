package com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting;

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
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortIPAddressAutoFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortPPOEFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.WANPortStaticIPAddressFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType.WirelessTypeOneFragment;
import com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting.wrelelessType.WirelessTypeTwoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWDSInfoParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWiFiDetailParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWDSInfoRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWiFiDetailRet;

public class GuestNetworkFragment extends Fragment {

    @BindView(R.id.tab)
    TabLayout tab;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;
    private WirelessTypeOneFragment wirelessTypeOneFragment;
    private WirelessTypeTwoFragment wirelessTypeTwoFragment;
    private Fragment currentFragment;
    private FragmentManager childFragmentManager;

    public static GuestNetworkFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        GuestNetworkFragment guestFragment = new GuestNetworkFragment();
        Bundle args = new Bundle();
        guestFragment.setArguments(args);
        return guestFragment;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
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
    }

    private void initView() {

        tab.addTab(tab.newTab().setText(getString(R.string.wireless_one)));
        tab.addTab(tab.newTab().setText(getString(R.string.wireless_two)));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                GetWiFiDetailParam getWiFiDetailParam = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
                switch (tab.getPosition()) {
                    case 0:
                        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                                .observeOn(Schedulers.trampoline())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                        break;
                    case 1:
                        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                                .observeOn(Schedulers.trampoline())
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                        break;
                    default:
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
        GetWiFiDetailParam getWiFiDetailParam = new GetWiFiDetailParam(LocalApi.DEFAULT_APP_API_VERSION);
        mLocalApi.executeApiWithSingleResponse(getWiFiDetailParam, GetWiFiDetailRet.class)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetWiFiDetailRet getWiFiDetailRet) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

//    private class DevicesManagementAdapter extends FragmentPagerAdapter {
//
//        private String[] mTitles;
//        private LocalApi mLocalApi;
//        private List<Fragment> fragmentList = new ArrayList<>();
//
//        DevicesManagementAdapter(FragmentManager fm, LocalApi localApi, String... titles) {
//            super(fm);
//            mLocalApi = localApi;
//            mTitles = titles;
//            fragmentList.add(WirelessTypeOneFragment.newInstance(mLocalApi));
//            fragmentList.add(WirelessTypeTwoFragment.newInstance(mLocalApi));
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
//    }

//    /**
//     * 切换Fragment
//     *
//     * @param fromFragment：需要隐藏的Fragment
//     * @param toFragment：需要显示的Fragment
//     */
//    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
//        if (fromFragment != toFragment) {
//            FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
//            if (!toFragment.isAdded()) {
//                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_guest, toFragment).commit();
//            } else {
//                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
//            }
//        }
//    }
}
