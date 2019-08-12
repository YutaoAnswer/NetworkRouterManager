package com.trigletop.networkroutermanager.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.main.AccountManagermentFragment;
import com.trigletop.networkroutermanager.view.fragment.main.AdvancedSettingFragment;
import com.trigletop.networkroutermanager.view.fragment.main.CommonSettingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;

/**
 * 主页
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tabLayout)
    SegmentTabLayout mTabLayout;

    private int mPosition;
    private static final int tabLayout_router = 0;
    private static final int tabLayout_setting = 1;
    private static final int tabLayout_account = 2;

    private Routers routers;
    private List<Routers> routersList;
    private List<WiFiInfo> wifiInfoList;
    private List<Fragment> fragmentList;

    private FragmentManager fragmentManager;
    private CommonSettingFragment commonSettingFragment;
    private AdvancedSettingFragment advancedSettingFragment;
    private AccountManagermentFragment accountFragment;
    private long exitTime;
    private SFUser sfUser;
    private LocalApi localApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView();
    }

    //todo 回退栈
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(MainActivity.this, R.string.msg_return, Toast.LENGTH_SHORT).show();
//            // 计算两次返回键按下的时间差
//            exitTime = System.currentTimeMillis();
//        } else {
//            // 关闭应用程序
//            finish();
//            // 返回桌面操作
//            // Intent home = new Intent(Intent.ACTION_MAIN);
//            // home.addCategory(Intent.CATEGORY_HOME);
//            // startActivity(home);
//        }
//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            // 判断间隔时间 大于2秒就退出应用
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                Toast.makeText(MainActivity.this, R.string.msg_return, Toast.LENGTH_SHORT).show();
//                // 计算两次返回键按下的时间差
//                exitTime = System.currentTimeMillis();
//            } else {
//                // 关闭应用程序
//                finish();
//                // 返回桌面操作
//                // Intent home = new Intent(Intent.ACTION_MAIN);
//                // home.addCategory(Intent.CATEGORY_HOME);
//                // startActivity(home);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 初始化
     */
    private void init() {
        localApi = new LocalApi(LocalApi.DEFAULT_APP_API_VERSION);

        fragmentManager = getSupportFragmentManager();
        commonSettingFragment = CommonSettingFragment.newInstance();
        advancedSettingFragment = AdvancedSettingFragment.newInstance();
        accountFragment = AccountManagermentFragment.newInstance(localApi);
        fragmentList = new ArrayList<>();
        fragmentList.add(commonSettingFragment);
        fragmentList.add(advancedSettingFragment);
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
        mTabLayout.setTabData(Data.title_data);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case tabLayout_router:
                        switchFragment(fragmentList.get(mPosition), commonSettingFragment);
                        mPosition = position;
                        break;
                    case tabLayout_setting:
                        switchFragment(fragmentList.get(mPosition), advancedSettingFragment);
                        mPosition = position;
                        break;
                    case tabLayout_account:
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

    //todo 首页替换Fragment内容需要优化，已发现bug,router　Fragment 点进去后的详情页  初步判断和回退栈有关,和要实现的另一个功能onBackPress　onKeyDown这两个方法有关 回退栈
    //todo 然后就是和顶部的切换会有冲突，需要优化

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
