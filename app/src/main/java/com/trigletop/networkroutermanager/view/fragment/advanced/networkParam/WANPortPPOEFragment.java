package com.trigletop.networkroutermanager.view.fragment.advanced.networkParam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;

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

public class WANPortPPOEFragment extends Fragment {

    private static LocalApi mLocalApi;

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_psw)
    EditText etPsw;
    private Unbinder unbinder;

    public static WANPortPPOEFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        WANPortPPOEFragment wanPortPPOEFragment = new WANPortPPOEFragment();
        Bundle args = new Bundle();
        wanPortPPOEFragment.setArguments(args);
        return wanPortPPOEFragment;
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
        View view = inflater.inflate(R.layout.fragment_wanportppoe, container, false);
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

    private void initData() {

    }

    private void initView() {

    }

    private void init() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_ppoe_save)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String password = etPsw.getText().toString();
        if (!account.isEmpty() && !password.isEmpty()) {
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
                    // TODO: 19-8-6 实现弹框提示
                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                    dialogBuilder
                            .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                            .withTitleColor(R.color.cyan)
                            .withMessage(getActivity().getString(R.string.setting_successful))
                            .withMessageColor("#FFFFFFFF")
                            .withEffect(Effectstype.SlideBottom)
                            .withDuration(700)
                            .show();
                }

                @Override
                public void onError(Throwable e) {
                }

            });
        } else {
            // TODO: 19-8-6 需要修改
            NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
            dialogBuilder
                    .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.network_mode))
                    .withTitleColor(R.color.cyan)
                    .withMessage(getActivity().getString(R.string.setting_unsuccessful))
                    .withMessageColor("#FFFFFFFF")
                    .withEffect(Effectstype.SlideBottom)
                    .withDuration(700)
                    .show();
        }
    }
}
