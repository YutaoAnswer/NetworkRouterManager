package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.CommandParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.CommandRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRet;

public class ResetFragment extends Fragment {

    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static ResetFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        ResetFragment resetFragment = new ResetFragment();
        Bundle args = new Bundle();
        resetFragment.setArguments(args);
        return resetFragment;
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
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
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

    @OnClick(R.id.btn_reset)
    public void onViewClicked() {
        CommandParam commandParam = new CommandParam(LocalApi.DEFAULT_APP_API_VERSION);
        commandParam.setCmd(2);
        mLocalApi.executeApiWithSingleResponse(commandParam, CommandRet.class)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CommandRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CommandRet commandRet) {
                        Toast.makeText(getContext(), "重置成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void init() {

    }

    private void initView() {

    }

    private void initData() {

    }

}
