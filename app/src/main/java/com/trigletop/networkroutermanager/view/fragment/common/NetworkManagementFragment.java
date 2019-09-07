package com.trigletop.networkroutermanager.view.fragment.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network_managment, container, false);
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

    private void initData() {
        Single<GetWanTypeRet> getWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(new GetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION), GetWanTypeRet.class);
        getWanTypeRetSingle.subscribe(new SingleObserver<GetWanTypeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWanTypeRet getWanTypeRet) {
                int type = getWanTypeRet.getType();//连接类型
                switch (type) {
                    case 0://dhcp
                        viewPager.setCurrentItem(2, true);
                        break;
                    case 1://ppoe拨号
                        viewPager.setCurrentItem(0, true);
                        break;
                    case 2://static ip
                        viewPager.setCurrentItem(1, true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                // TODO: 19-8-30 刷新获取
                Toast.makeText(getActivity(), "获取数据失败，请重新获取", Toast.LENGTH_SHORT).show();
            }
        });
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
