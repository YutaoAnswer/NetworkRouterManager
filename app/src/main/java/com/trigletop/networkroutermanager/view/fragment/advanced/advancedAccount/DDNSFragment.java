package com.trigletop.networkroutermanager.view.fragment.advanced.advancedAccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.view.fragment.advanced.networkParam.BindingSettingFragment;

public class DDNSFragment extends Fragment {

    public static DDNSFragment newInstance() {
        DDNSFragment ddnsFragment = new DDNSFragment();
        Bundle args = new Bundle();
        ddnsFragment.setArguments(args);
        return ddnsFragment;
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
        return inflater.inflate(R.layout.fragment_ddns, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
