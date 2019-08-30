package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.OtaCheckParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.OtaUpgradeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.OtaCheckRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.OtaUpgradeRet;

/**
 * 　固件升级
 */
public class UpdateFragment extends Fragment {

    @BindView(R.id.et_software)
    EditText etSoftware;
    @BindView(R.id.tv_software)
    TextView tvSoftware;
    @BindView(R.id.tv_hardware)
    TextView tvHardware;
    @BindView(R.id.cb_software)
    CheckBox cbSoftware;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static UpdateFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        UpdateFragment updateFragment = new UpdateFragment();
        Bundle args = new Bundle();
        updateFragment.setArguments(args);
        return updateFragment;
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
        View view = inflater.inflate(R.layout.fragment_update, container, false);
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

    @OnClick({R.id.btn_add, R.id.btn_config, R.id.btn_update, R.id.btn_ota_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                // TODO: 19-8-7 添加文件

                break;
            case R.id.btn_config:

                break;
            case R.id.btn_update:

                break;
            case R.id.btn_ota_update:
                // TODO: 19-8-7 优化
                NiftyDialogBuilder niftyDialogBuilder = NiftyDialogBuilder.getInstance(getContext());
                niftyDialogBuilder
                        .withDuration(700)
                        .setCustomView(R.layout.custom_view_otaupdate, getContext())
                        .withDialogColor("#0096a6")
                        .withButton1Text("取消")
                        .withButton2Text("确定")
                        .setButton1Click(v -> {
                            new Thread() {
                                public void run() {
                                    //模拟按钮回退
                                    try {
                                        Instrumentation inst = new Instrumentation();
                                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                        })
                        .setButton2Click(v -> {
                            //检测更新
                            OtaCheckParam otaCheckParam = new OtaCheckParam(LocalApi.DEFAULT_APP_API_VERSION);
                            Single<OtaCheckRet> otaCheckRetSingle = mLocalApi.executeApiWithSingleResponse(otaCheckParam, OtaCheckRet.class);
                            otaCheckRetSingle.subscribe(new SingleObserver<OtaCheckRet>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(OtaCheckRet otaCheckRet) {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                            new Thread() {
                                public void run() {
                                    //模拟按钮回退
                                    try {
                                        Instrumentation inst = new Instrumentation();
                                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                        })
                        .withEffect(Effectstype.SlideBottom)
                        .isCancelableOnTouchOutside(true);
                break;
        }
    }

    private void init() {

    }

    private void initData() {

    }

    private void initView() {
    }

    /**
     * 更新
     */
    private void update() {
        OtaUpgradeParam otaUpgradeParam = new OtaUpgradeParam(LocalApi.DEFAULT_APP_API_VERSION);
        Single<OtaUpgradeRet> upgradeRetSingle = mLocalApi.executeApiWithSingleResponse(otaUpgradeParam, OtaUpgradeRet.class);
        upgradeRetSingle.subscribe(new SingleObserver<OtaUpgradeRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(OtaUpgradeRet otaUpgradeRet) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
