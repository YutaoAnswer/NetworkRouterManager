package com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trigletop.networkroutermanager.R;

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
    @BindView(R.id.et_language)
    EditText etLanguage;

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
        SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat(getString(R.string.simpledateformat));
//        SimpleDateFormat simpleDateFormat_time = new SimpleDateFormat("HH:mm:ss");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        // TODO: 2019-09-05
        //  01-01 08:18:01.448 8825 8825 E AndroidRuntime: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(java.lang.CharSequence)' on a null object reference
        //  01-01 08:18:01.448 8825 8825 E AndroidRuntime: at com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.TimeAndLanguageFragment.initView(TimeAndLanguageFragment.java:62)
        //  01-01 08:18:01.448 8825 8825 E AndroidRuntime: at com.trigletop.networkroutermanager.view.fragment.advanced.deviceManagement.TimeAndLanguageFragment.onViewCreated(TimeAndLanguageFragment.java:49)
//        tvData.setText(String.valueOf(simpleDateFormat_date.format(date)));
//        tvTime.setText(simpleDateFormat_time.format(date));
    }

    private void initData() {

    }

    @OnClick({R.id.btn_time_save, R.id.btn_language_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_time_save:

                break;
            case R.id.btn_language_save:

                break;
        }
    }
}
