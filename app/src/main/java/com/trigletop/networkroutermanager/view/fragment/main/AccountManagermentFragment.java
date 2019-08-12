package com.trigletop.networkroutermanager.view.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.account.SetupWizardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class AccountManagermentFragment extends Fragment {

    private static final String TAG = AccountManagermentFragment.class.getSimpleName();

    @BindView(R.id.frameLayout_account)
    FrameLayout frameLayoutAccount;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    private Fragment currentFragment;
    private SetupWizardFragment setupWizardFragment;
    private FragmentManager fragmentManager;

    public static AccountManagermentFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        AccountManagermentFragment accountFragment = new AccountManagermentFragment();
        Bundle args = new Bundle();
        accountFragment.setArguments(args);
        return accountFragment;
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initView();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        setupWizardFragment = SetupWizardFragment.newInstance(mLocalApi);
        fragmentManager = getFragmentManager();
    }

    private void initView() {
        //初始化界面　首页展示设置向导页面
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.frameLayout_account, setupWizardFragment)
                .commit();
        currentFragment = setupWizardFragment;
    }

    private void initData() {

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
                fragmentTransaction.hide(fromFragment).add(R.id.frameLayout_common_setting, toFragment).commit();
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commit();
            }
        }
    }

    @OnClick(R.id.ll_setup_wizard)
    public void onViewClicked() {
        switchFragment(currentFragment, setupWizardFragment);
        currentFragment = setupWizardFragment;
    }

}
