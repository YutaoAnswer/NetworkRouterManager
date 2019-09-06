package com.trigletop.networkroutermanager.view.fragment.common.devicesManagement;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.DevicesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetSpeedParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetSpeedRet;

public class ConnectedFragment extends Fragment {

    private static final String TAG = ConnectedFragment.class.getSimpleName();

    @BindView(R.id.rcy_connected)
    TvRecyclerView rcyConnected;
    private Unbinder unbinder;

    private static LocalApi mLocalApi;

    public static ConnectedFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        ConnectedFragment connectedFragment = new ConnectedFragment();
        Bundle args = new Bundle();
        connectedFragment.setArguments(args);
        return connectedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connected, container, false);
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

    private void init() {

    }

    private void initView() {
        //RecyclerView
        TvRecyclerView.openDEBUG();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyConnected.setLayoutManager(manager);

        int itemSpace = getResources().getDimensionPixelSize(R.dimen.recyclerView_item_space);
        rcyConnected.addItemDecoration(new SpaceItemDecoration(itemSpace));
        rcyConnected.setSelectPadding(35, 34, 35, 38);
    }

    private void initData() {
        // TODO: 19-8-13 加效果　加载刷新
        Single<GetDeviceRet> getDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRet.class);
        getDeviceRetSingle.subscribe(new SingleObserver<GetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(GetDeviceRet getDeviceRet) {
                DevicesAdapter devicesAdapter = new DevicesAdapter(getActivity(), "Connected", mLocalApi);
                List<Device> current = getDeviceRet.getList();
                ArrayList<Device> temporary = new ArrayList<>();
                for (Device device : current) {
                    if (device.getAuthority().getInternet() != 1)
                        temporary.add(device);
                }
                current.removeAll(temporary);
                devicesAdapter.setDeviceList(current);
                rcyConnected.setAdapter(devicesAdapter);
                rcyConnected.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
                    @Override
                    public void onItemViewClick(View view, int position) {
                        Device device = devicesAdapter.getDeviceList().get(position);
                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
                        dialogBuilder
                                .withTitle(Objects.requireNonNull(getActivity()).getString(R.string.upload_download_limit))
                                .withDuration(700)
                                .setCustomView(R.layout.custom_view_connected, getContext())
                                .withDialogColor("#0096a6")
                                .withEffect(Effectstype.SlideBottom)
                                .isCancelableOnTouchOutside(true);

                        EditText etUpload = dialogBuilder.findViewById(R.id.et_upload);
                        EditText etDownload = dialogBuilder.findViewById(R.id.et_download);
                        if (device.getAuthority().getLimitup() != -1) {
                            etUpload.setText(String.valueOf(device.getAuthority().getLimitup()));
                        }
                        if (device.getAuthority().getLimitup() != -1) {
                            etDownload.setText(String.valueOf(device.getAuthority().getLimitdown()));
                        }
                        Button btnForbidden = dialogBuilder.findViewById(R.id.btn_forbidden);
                        long limitdown = device.getAuthority().getLimitdown();
                        long limitup = device.getAuthority().getLimitup();
                        if (limitdown == -1) {
                            etDownload.setHint(getActivity().getResources().getString(R.string.download));
                        } else {
                            etDownload.setText(String.valueOf(limitdown));
                        }
                        if (limitup == -1) {
                            etUpload.setHint(getActivity().getResources().getString(R.string.upload));
                        } else {
                            etUpload.setText(String.valueOf(limitup));
                        }
                        btnForbidden.setOnClickListener(v -> forbidden(device.getMac()));
                        dialogBuilder
                                .setButton1Click(v -> {
                                    String upload = etUpload.getText().toString();
                                    if (!upload.equals("")) {
                                        limitUpload(device.getMac(), Long.valueOf(upload), dialogBuilder);
                                    } else {
                                        Toast.makeText(getActivity(), "请输入上传限速值", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        dialogBuilder
                                .setButton2Click(v -> {
                                    String download = etDownload.getText().toString();
                                    if (!download.equals("")) {
                                        Toast.makeText(getActivity(), "限制下载" + download + "Mac地址为" + device.getMac(), Toast.LENGTH_SHORT).show();
                                        limitDownload(device.getMac(), Long.valueOf(download), dialogBuilder);
                                    } else {
                                        Toast.makeText(getActivity(), "请输入下载限速值", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        dialogBuilder.show();
                    }

                    @Override
                    public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
                        // TODO: 19-8-2 添加内容

                    }
                });
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    /**
     * 禁用设备连接网络
     *
     * @param mac 　禁用设备Mac地址
     */
    private void forbidden(String mac) {
        SetDeviceParam setDeviceParam = new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, mac);
        setDeviceParam.setMac(mac.replaceAll(":", "_"));
        setDeviceParam.setInternet(0);
        Single<SetDeviceRet> setDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(setDeviceParam, SetDeviceRet.class);
        setDeviceRetSingle.subscribe(new SingleObserver<SetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(SetDeviceRet setDeviceRet) {
                Toast.makeText(getActivity(), "禁用设备成功", Toast.LENGTH_SHORT).show();
                initData();
            }

            @Override
            public void onError(Throwable e) {
            }
        });
//        SetSpeedParam setSpeedParam = new SetSpeedParam(LocalApi.DEFAULT_APP_API_VERSION);
//        setSpeedParam.setMac(mac);
//        setSpeedParam.setEnable();
//        Single<SetSpeedRet> setSpeedRetSingle = mLocalApi.executeApiWithSingleResponse(setSpeedParam, SetSpeedRet.class);
//        setSpeedRetSingle.subscribe(new SingleObserver<SetSpeedRet>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Toast.makeText(getActivity(), "onSubscribe", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess(SetSpeedRet setSpeedRet) {
//                Toast.makeText(getActivity(), "禁用设备成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 限制设备下载速率
     *
     * @param mac           设备Mac地址
     * @param downloadSpeed 下载速率设置值
     */
    private void limitDownload(String mac, long downloadSpeed, NiftyDialogBuilder niftyDialogBuilder) {
        SetDeviceParam setDeviceParam = new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, mac);
        Log.d(TAG, "limitDownload: " + downloadSpeed);
        setDeviceParam.setMac(mac.replaceAll(":", "_"));
        setDeviceParam.setLimitdown(downloadSpeed);
        Single<SetDeviceRet> setDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(setDeviceParam, SetDeviceRet.class);
        setDeviceRetSingle.subscribe(new SingleObserver<SetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(SetDeviceRet setDeviceRet) {
                niftyDialogBuilder.dismiss();
                Toast.makeText(getActivity(), "设置下载速率为" + downloadSpeed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }
        });

//        SetSpeedParam setSpeedParam = new SetSpeedParam(LocalApi.DEFAULT_APP_API_VERSION);
//        setSpeedParam.setMac(mac);
//        setSpeedParam.setLimitdown(downloadSpeed);
//        Single<SetSpeedRet> setSpeedRetSingle = mLocalApi.executeApiWithSingleResponse(setSpeedParam, SetSpeedRet.class);
//        setSpeedRetSingle.subscribe(new SingleObserver<SetSpeedRet>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onSuccess(SetSpeedRet setSpeedRet) {
//                Toast.makeText(getActivity(), "设置下载速率为" + downloadSpeed, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        });

    }

    /**
     * 限制设备上传速率
     *
     * @param mac         设备Mac地址
     * @param uploadSpeed 　上传速率设置值
     */
    private void limitUpload(String mac, Long uploadSpeed, NiftyDialogBuilder niftyDialogBuilder) {
        SetDeviceParam setDeviceParam = new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, mac);
        setDeviceParam.setMac(mac.replaceAll(":", "_"));
        setDeviceParam.setLimitup(uploadSpeed);
        Single<SetDeviceRet> setDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(setDeviceParam, SetDeviceRet.class);
        setDeviceRetSingle.subscribe(new SingleObserver<SetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(SetDeviceRet setDeviceRet) {
                Log.d(TAG, "onSuccess: ");
                niftyDialogBuilder.dismiss();
                Toast.makeText(getActivity(), "设置上传速率" + uploadSpeed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
            }
        });
//        SetSpeedParam setSpeedParam = new SetSpeedParam(LocalApi.DEFAULT_APP_API_VERSION);
//        setSpeedParam.setMac(mac);
//        setSpeedParam.setLimitup(uploadSpeed);
//        Single<SetSpeedRet> setSpeedRetSingle = mLocalApi.executeApiWithSingleResponse(setSpeedParam, SetSpeedRet.class);
//        setSpeedRetSingle.subscribe(new SingleObserver<SetSpeedRet>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(SetSpeedRet setSpeedRet) {
//                Toast.makeText(getActivity(), "设置上传速率" + uploadSpeed, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        });
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = space;
            outRect.top = space;
        }
    }

}
