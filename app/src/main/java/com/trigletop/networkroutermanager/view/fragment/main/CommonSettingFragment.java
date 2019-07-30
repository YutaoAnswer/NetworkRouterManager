package com.trigletop.networkroutermanager.view.fragment.main;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.SiUtil;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;


public class CommonSettingFragment extends Fragment {

    private static final String TAG = CommonSettingFragment.class.getSimpleName();

    public static CommonSettingFragment newInstance() {
        CommonSettingFragment commonSettingFragment = new CommonSettingFragment();
        Bundle args = new Bundle();
        commonSettingFragment.setArguments(args);
        return commonSettingFragment;
    }

    // TODO: 19-7-30  把网络请求放在Fragment中
    @BindView(R.id.rcy_router)
    TvRecyclerView rcyRouter;
    Unbinder unbinder;

    private SiUtil mSiUtil;
    private LocalApi localApi;

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
        View view = inflater.inflate(R.layout.fragment_router, container, false);
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        mSiUtil = new SiUtil(getActivity());
        localApi = mSiUtil.localApiInit();
    }

    private void initView() {
        //RecyclerView
        rcyRouter.requestFocus();
        TvRecyclerView.openDEBUG();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyRouter.setLayoutManager(manager);

        int itemSpace = getResources().getDimensionPixelSize(R.dimen.recyclerView_item_space);
        rcyRouter.addItemDecoration(new SpaceItemDecoration(itemSpace));
//        NormalAdapter mAdapter = new NormalAdapter(getActivity(), TAG);
//        rcyRouter.setAdapter(mAdapter);
//
//        rcyRouter.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
//            @Override
//            public void onItemViewClick(View view, int position) {
//                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
//                // TODO: 19-7-29 默认构造方法
//                DeviceDetailFragment deviceDetailFragment = new DeviceDetailFragment();
//                fragmentTransaction.addToBackStack(null).add(R.id.frameLayout, deviceDetailFragment).replace(R.id.frameLayout, deviceDetailFragment).commit();
//            }
//
//            @Override
//            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {
//
//            }
//        });
    }

    private void initData() {
        mSiUtil.getDeviceRet(rcyRouter, localApi, getActivity());
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
