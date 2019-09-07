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
import android.widget.Toast;

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

import static sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.OtaUpgradeRet.MSG_DOWNLOAD_ERROR;
import static sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.OtaUpgradeRet.MSG_NOT_UPGRADING;
import static sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.OtaUpgradeRet.MSG_START_UPGRADE;

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
                        .setButton1Click(v -> niftyDialogBuilder.dismiss())
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
                                    if (otaCheckRet.getCode() == 0) {
                                        //本地版本
                                        String romVersion = otaCheckRet.getRomversion();
                                        //服务器上的ota版本
                                        String otaVersion = otaCheckRet.getOtaversion();

                                        if (!romVersion.contains(otaVersion)) {
                                            //可以进行ota升级
                                            new Thread(() -> update());
                                        } else {
                                            //已经是最新版本，不需要升级
                                            NiftyDialogBuilder instance = NiftyDialogBuilder.getInstance(getContext());
                                            instance
                                                    .withDuration(700)
                                                    .setCustomView(R.layout.custom_view_update_nor, getContext())
                                                    .withDialogColor("#0096a6")
                                                    .withButton1Text("确定")
                                                    .setButton1Click(v1 -> instance.dismiss())
                                                    .show();
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO: 2019-09-06 NullPointException java.lang.String android.Content.getPackageName()
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    NiftyDialogBuilder instance = NiftyDialogBuilder.getInstance(getContext());
                                    instance
                                            .withDuration(700)
                                            .setCustomView(R.layout.custom_view_update, getContext())
                                            .withDialogColor("#0096a6")
                                            .withButton1Text("确定")
                                            .withEffect(Effectstype.SlideBottom)
                                            .setButton1Click(v1 -> instance.dismiss())
                                            .show();
                                }
                            });
                            niftyDialogBuilder.dismiss();
                        })
                        .withEffect(Effectstype.SlideBottom)
                        .isCancelableOnTouchOutside(true)
                        .show();
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
                NiftyDialogBuilder niftyDialogBuilder = new NiftyDialogBuilder(getContext());
                switch (otaUpgradeRet.getStatus()) {
                    case OtaUpgradeRet.STATUS_START_UPGRADE://开始下载镜像文件
                    case OtaUpgradeRet.STATUS_DOWNLOADING_MIRROR://下载镜像文件中
                    case OtaUpgradeRet.STATUS_START_BURNING:
                        update();
                        break;
                    case OtaUpgradeRet.STATUS_DOWNLOAD_MIRROR_ERROR://下载镜像出错
                        niftyDialogBuilder
                                .withDuration(700)
                                .setCustomView(R.layout.custom_view_status_download_mirror_error, getContext())
                                .withDialogColor("#0096a6")
                                .withButton1Text("确定")
                                .setButton1Click(v -> update())
                                .withEffect(Effectstype.SlideBottom)
                                .isCancelableOnTouchOutside(true)
                                .show();
                        break;
                    case OtaUpgradeRet.STATUS_NOT_UPGRADING://升级完成
                        niftyDialogBuilder
                                .withDuration(700)
                                .setCustomView(R.layout.custom_view_status_not_upgrading, getContext())
                                .withDialogColor("#0096a6")
                                .withButton1Text("确定")
                                .setButton1Click(v -> niftyDialogBuilder.dismiss())
                                .withEffect(Effectstype.SlideBottom)
                                .isCancelableOnTouchOutside(true)
                                .show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
