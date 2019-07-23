package com.trigletop.networkroutermanager.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;
import com.trigletop.networkroutermanager.view.fragment.AccountFragment;
import com.trigletop.networkroutermanager.view.fragment.RouterFragment;
import com.trigletop.networkroutermanager.view.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;

/**
 * 主页
 */
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    //    @BindView(R.id.tv_recycler_view)
//    TvRecyclerView mTvRecyclerView;
    @BindView(R.id.tabLayout)
    SegmentTabLayout mTabLayout;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    private int mPosition;
    private static final int tabLayout_router = 0;
    private static final int tabLayout_setting = 1;
    private static final int tabLayout_account = 2;

    private Routers routers;
    private List<Routers> routersList;
    private List<WiFiInfo> wifiInfoList;
    private List<Fragment> fragmentList;

    private FragmentManager fragmentManager;
    private RouterFragment routerFragment;
    private SettingFragment settingFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView();
    }

    /**
     * 初始化
     */
    private void init() {
        SiUtil siUtil = new SiUtil(MainActivity.this);
        siUtil.login();
        siUtil.bindRouter();
        siUtil.getRouters();
        siUtil.getWifiObserve();

        routers = siUtil.getmRouters();//获取当前要显示信息的显示路由器
        routersList = siUtil.getRoutersList();//获取绑定的路由器列表

        fragmentManager = getFragmentManager();
        routerFragment = new RouterFragment();
        settingFragment = new SettingFragment();
        accountFragment = new AccountFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(routerFragment);
        fragmentList.add(settingFragment);
        fragmentList.add(accountFragment);
    }

    /**
     * 初始化View
     */
    private void initView() {
        //初始化界面　首页展示路由界面　
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout, fragmentList.get(tabLayout_router))
                .commit();

        //TabLayout
        mTabLayout.setTabData(Data.data);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case tabLayout_router:
                        Log.d(TAG, "onTabSelect: " + position);
                        switchFragment(fragmentList.get(mPosition), routerFragment);
                        mPosition = position;
                        break;
                    case tabLayout_setting:
                        Log.d(TAG, "onTabSelect: " + position);
                        switchFragment(fragmentList.get(mPosition), settingFragment);
                        mPosition = position;
                        break;
                    case tabLayout_account:
                        Log.d(TAG, "onTabSelect: " + position);
                        switchFragment(fragmentList.get(mPosition), accountFragment);
                        mPosition = position;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mTabLayout.setCurrentTab(0);
    }

    /**
     * 切换Fragment
     *
     * @param fromFragment：需要隐藏的Fragment
     * @param toFragment：需要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (fromFragment != toFragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

}
