package com.trigletop.networkroutermanager.view.fragment.advanced.wirelessSetting;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetWDSScanParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWDSScanInfoRet;

public class WDSFragment extends Fragment {

    private static final String TAG = WDSFragment.class.getSimpleName();

    @BindView(R.id.rcyWDS)
    RecyclerView rcyWDS;

    private Unbinder unbinder;
    private static LocalApi mLocalApi;

    public static WDSFragment newInstance(LocalApi lolcalApi) {
        mLocalApi = lolcalApi;
        WDSFragment wdsFragment = new WDSFragment();
        Bundle args = new Bundle();
        wdsFragment.setArguments(args);
        return wdsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wds, container, false);
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

    private void init() {

    }

    private void initView() {
        initHintDialog();

        rcyWDS.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {

    }

    private void initHintDialog() {
        NiftyDialogBuilder dialogBuilder = new NiftyDialogBuilder(getContext());
        dialogBuilder
                .withTitle("WDS网组向导")
                .withDuration(700)
                .setCustomView(R.layout.custom_view_wds_hint, getContext())
                .withButton1Text("下一步")
                .setButton1Click(v -> {
                    dialogBuilder.dismiss();
                    chooseNetwork();
                })
                .show();
    }

    private void chooseNetwork() {
        NiftyDialogBuilder niftyDialogBuilder = new NiftyDialogBuilder(getContext());
        niftyDialogBuilder
                .withTitle("请选择一个网络")
                .withDuration(700)
                .setCustomView(R.layout.custom_view_choose_network, getContext());
        Button networkOne = niftyDialogBuilder.findViewById(R.id.btn_network_one);
        Button networkTwo = niftyDialogBuilder.findViewById(R.id.btn_network_two);
        networkOne.setOnClickListener(v -> getWDSScan("2.4G"));
        networkTwo.setOnClickListener(v -> getWDSScan("5G"));
        niftyDialogBuilder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void getWDSScan(String band) {
        GetWDSScanParam getWDSScanParam = new GetWDSScanParam(LocalApi.DEFAULT_APP_API_VERSION);
        getWDSScanParam.setBand(band);
        Single<GetWDSScanInfoRet> getWDSScanInfoRetSingle = mLocalApi.executeApiWithSingleResponse(getWDSScanParam, GetWDSScanInfoRet.class);
        getWDSScanInfoRetSingle.subscribe(new SingleObserver<GetWDSScanInfoRet>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetWDSScanInfoRet getWDSScanInfoRet) {
                switch (band) {
                    case "2.4G":

                        break;
                    case "5G":

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
