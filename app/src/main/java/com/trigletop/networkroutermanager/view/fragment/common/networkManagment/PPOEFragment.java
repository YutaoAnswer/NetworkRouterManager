package com.trigletop.networkroutermanager.view.fragment.common.networkManagment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWanTypeRet;

public class PPOEFragment extends Fragment {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_psw)
    EditText etPsw;
    private Unbinder unbinder;

    private SiUtil siUtil;
    private String password;
    private String account;

    private static LocalApi mLocalApi;

    public static PPOEFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        PPOEFragment ppoeFragment = new PPOEFragment();
        Bundle args = new Bundle();
        ppoeFragment.setArguments(args);
        return ppoeFragment;
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
        View view = inflater.inflate(R.layout.fragment_ppoe, container, false);
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void init() {
        siUtil = new SiUtil(getActivity());
    }

    private void initView() {
        account = etAccount.getText().toString();
        password = etPsw.getText().toString();
    }

    private void initData() {

    }

    @OnClick(R.id.btn_ppoe_save)
    public void onViewClicked() {
        if (account != null && password != null) {
//            siUtil.setWanType(mLocalApi, account, password, handler);
            SetWanTypeParam setWanTypeParam = new SetWanTypeParam(LocalApi.DEFAULT_APP_API_VERSION);
            setWanTypeParam.setPppnanme(account);
            setWanTypeParam.setPpppwd(password);
            Single<SetWanTypeRet> setWanTypeRetSingle = mLocalApi.executeApiWithSingleResponse(setWanTypeParam, SetWanTypeRet.class);
            setWanTypeRetSingle.subscribe(new SingleObserver<SetWanTypeRet>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onSuccess(SetWanTypeRet setWanTypeRet) {
//                    Message message = new Message();
//                    message.what = Data.handler_message_setWanType_PPOE;
//                    message.obj = "setDevice";
//                    handler.sendMessage(message);
                    // TODO: 19-8-6 实现弹框提示
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.upload_download_limit))
                            .withDuration(700)
                            .show();
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        } else {
            // TODO: 19-8-6 需要修改
            Toast.makeText(getContext(), "错误", Toast.LENGTH_SHORT).show();
        }
    }

}