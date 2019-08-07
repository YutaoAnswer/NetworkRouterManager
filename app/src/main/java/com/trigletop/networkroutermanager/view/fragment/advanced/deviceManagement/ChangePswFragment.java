package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetPasswordParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetPasswordRet;

public class ChangePswFragment extends Fragment {

    private static final String TAG = ChangePswFragment.class.getSimpleName();

    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    private Unbinder unbinder;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    private static LocalApi mLocalApi;

    public static ChangePswFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        ChangePswFragment changePswFragment = new ChangePswFragment();
        Bundle args = new Bundle();
        changePswFragment.setArguments(args);
        return changePswFragment;
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
        View view = inflater.inflate(R.layout.fragment_change_psw, container, false);
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

    @OnClick(R.id.btn_change_psw_save)
    public void onViewClicked() {
        oldPassword = etOldPassword.getText().toString();
        newPassword = etNewPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();
        if (!oldPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
            // TODO: 19-8-7 保存密码
            SetPasswordParam setPasswordParam = new SetPasswordParam(LocalApi.DEFAULT_APP_API_VERSION);
            setPasswordParam.setOldpwd(oldPassword);
            setPasswordParam.setNewpwd(newPassword);
            Single<SetPasswordRet> setPasswordRetSingle = mLocalApi.executeApiWithSingleResponse(setPasswordParam, SetPasswordRet.class);
            setPasswordRetSingle.subscribe(new SingleObserver<SetPasswordRet>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe: ");

                }

                @Override
                public void onSuccess(SetPasswordRet setPasswordRet) {
                    Log.d(TAG, "onSuccess: ");
                    // TODO: 19-8-7 弹窗

                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: ");

                }
            });
        } else {
            // TODO: 19-8-7  弹窗

        }
    }

    private void init() {

    }

    private void initView() {

    }

    private void initData() {

    }
}
