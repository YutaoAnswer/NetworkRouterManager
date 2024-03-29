package com.trigletop.networkroutermanager.view.fragment.common.devicesManagement;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.DevicesAdapter;
import com.trigletop.networkroutermanager.utils.SiUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRet;

public class ForbiddenFragment extends Fragment {

    private static final String TAG = ForbiddenFragment.class.getSimpleName();

    @BindView(R.id.rcy_connected)
    TvRecyclerView rcyConnected;
    private Unbinder unbinder;

    private SiUtil siUtil;
    private LocalApi mLocalApi;

    public static ForbiddenFragment newInstance() {
        ForbiddenFragment forbiddenFragment = new ForbiddenFragment();
        Bundle args = new Bundle();
        forbiddenFragment.setArguments(args);
        return forbiddenFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forbidden, container, false);
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

    private void init() {
        siUtil = new SiUtil(getActivity());
        mLocalApi = siUtil.localApiInit();
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
        Single<GetDeviceRet> getDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(new GetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION), GetDeviceRet.class);
        getDeviceRetSingle.subscribe(new SingleObserver<GetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(GetDeviceRet getDeviceRet) {
                DevicesAdapter devicesAdapter = new DevicesAdapter(getActivity(), "Forbidden", mLocalApi);
                List<Device> current = getDeviceRet.getList();
                ArrayList<Device> temporary = new ArrayList<>();
                for (Device device : current) {
                    if (device.getAuthority().getInternet() != 0)
                        temporary.add(device);
//                        getDeviceRet.getList().remove(device);
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
                                .setCustomView(R.layout.custom_view_forbidden, getContext())
                                .withButton1Text("确定")
                                .setButton1Click(v -> liftBan(device.getMac(), dialogBuilder))
                                .show();
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
     * 解除禁用设备连接网络
     *
     * @param mac 　解除禁用设备Mac地址
     */
    private void liftBan(String mac, NiftyDialogBuilder niftyDialogBuilder) {
        SetDeviceParam setDeviceParam = new SetDeviceParam(LocalApi.DEFAULT_APP_API_VERSION, mac);
        setDeviceParam.setMac(mac);
        setDeviceParam.setInternet(1);
        Single<SetDeviceRet> setDeviceRetSingle = mLocalApi.executeApiWithSingleResponse(setDeviceParam, SetDeviceRet.class);
        setDeviceRetSingle.subscribe(new SingleObserver<SetDeviceRet>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(SetDeviceRet setDeviceRet) {
                initData();
                niftyDialogBuilder.dismiss();
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
