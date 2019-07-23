package com.trigletop.networkroutermanager.view.fragment;

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
import com.trigletop.networkroutermanager.adapter.NormalAdapter;

import app.com.tvrecyclerview.TvRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {

    @BindView(R.id.rcy_setting)
    TvRecyclerView rcySetting;
    Unbinder unbinder;

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
        View view = inflater.inflate(R.layout.setting_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        //RecyclerView
        TvRecyclerView.openDEBUG();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcySetting.setLayoutManager(manager);

        int itemSpace = getResources().getDimensionPixelSize(R.dimen.recyclerView_item_space);
        rcySetting.addItemDecoration(new SpaceItemDecoration(itemSpace));
        NormalAdapter mAdapter = new NormalAdapter(getActivity());
        rcySetting.setAdapter(mAdapter);

        rcySetting.setOnItemStateListener(new TvRecyclerView.OnItemStateListener() {
            @Override
            public void onItemViewClick(View view, int position) {

            }

            @Override
            public void onItemViewFocusChanged(boolean gainFocus, View view, int position) {

            }
        });
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
