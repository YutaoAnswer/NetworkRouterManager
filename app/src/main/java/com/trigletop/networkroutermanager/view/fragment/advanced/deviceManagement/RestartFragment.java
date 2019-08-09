package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.CommandParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.CommandRet;

public class RestartFragment extends Fragment {

    private static LocalApi mLocalApi;
    private Unbinder unbinder;

    public static RestartFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        RestartFragment restartFragment = new RestartFragment();
        Bundle args = new Bundle();
        restartFragment.setArguments(args);
        return restartFragment;
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
        View view = inflater.inflate(R.layout.fragment_restart, container, false);
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

    private void initData() {

    }

    private void init() {

    }

    private void initView() {

    }

    @OnClick(R.id.btn_reset)
    public void onViewClicked() {
        CommandParam commandParam = new CommandParam(LocalApi.DEFAULT_APP_API_VERSION);
        commandParam.setCmd(0);

        mLocalApi.executeApiWithSingleResponse(commandParam, CommandRet.class)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CommandRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CommandRet commandRet) {
                        // TODO: 19-8-8 弹窗

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
