package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;

public class DiagnosticToolsFragment extends Fragment {

    private static LocalApi mLocalApi;

    public static DiagnosticToolsFragment newInstance(LocalApi localApi) {
        mLocalApi = localApi;
        DiagnosticToolsFragment diagnosticToolsFragment = new DiagnosticToolsFragment();
        Bundle args = new Bundle();
        diagnosticToolsFragment.setArguments(args);
        return diagnosticToolsFragment;
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
        return inflater.inflate(R.layout.fragment_diagnostic_tools, container, false);
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
