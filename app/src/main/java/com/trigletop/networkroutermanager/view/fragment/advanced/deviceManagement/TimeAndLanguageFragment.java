package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.adapter.LanguageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class TimeAndLanguageFragment extends Fragment {

    @BindView(R.id.et_time_zoom)
    EditText etTimeZoom;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_language)
    Button btnLanguage;

    public static TimeAndLanguageFragment newInstance() {
        TimeAndLanguageFragment timeAndLanguageFragment = new TimeAndLanguageFragment();
        Bundle args = new Bundle();
        timeAndLanguageFragment.setArguments(args);
        return timeAndLanguageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_language, container, false);
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

    }

    private void initData() {

    }

    @OnClick({R.id.btn_time_save, R.id.btn_language_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_time_save:

                break;
            case R.id.btn_language_save:
                NiftyDialogBuilder niftyDialogBuilder = new NiftyDialogBuilder(getContext());
                niftyDialogBuilder
                        .withTitle("语言设置")
                        .withDuration(700)
                        .setCustomView(R.layout.custom_view_language, getContext());
                RecyclerView rcyLanguage = niftyDialogBuilder.findViewById(R.id.rcy_language);
                LanguageAdapter languageAdapter = new LanguageAdapter();

                rcyLanguage.setAdapter(languageAdapter);

                niftyDialogBuilder
                        .withButton1Text("确定")
                        .withButton2Text("取消")
                        .setButton1Click(v -> {

                        })
                        .setButton2Click(v -> {

                        })
                        .show();
                break;
        }
    }

}
