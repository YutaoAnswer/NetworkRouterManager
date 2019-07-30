package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.ContantUtil;

import java.util.List;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;


public class NormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = NormalAdapter.class.getSimpleName();

    private Context mContext;
    private String mParam;

    private List<Device> deviceList;

    public NormalAdapter(Context context) {
        mContext = context;
    }

    public NormalAdapter(Context context, String param) {
        mContext = context;
        mParam = param;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        Log.d(TAG, "onBindViewHolder: position" + position);
        switch (mParam) {
            case "CommonSettingFragment":
                // TODO: 19-7-30 如果deviceList为空怎么办，则不会设置任何值
                if (deviceList != null && deviceList.size() > 0 && position < deviceList.size()) {
                    viewHolder.mName.setText(deviceList.get(position).getIp());
                }
                break;
            case "AdvancedSettingFragment":
                viewHolder.mName.setText(ContantUtil.SETTING_DATAS[position]);
                break;
            default:
                viewHolder.mName.setText(ContantUtil.TEST_DATAS[position]);
                break;
        }

        GradientDrawable drawable = (GradientDrawable) viewHolder.mFrameLayout.getBackground();
        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));
    }

    @Override
    public int getItemCount() {
        int itemCount;
        switch (mParam) {
            case "CommonSettingFragment":
                itemCount = deviceList.size();
                break;
            case "AdvancedSettingFragment":
                itemCount = ContantUtil.SETTING_DATAS.length;
                break;
            case "AccountManagermentFragment":
                // TODO: 19-7-30 模拟数据
                itemCount = ContantUtil.SETTING_DATAS.length;
                break;
            default:
                itemCount = ContantUtil.TEST_DATAS.length;
                break;
        }
        return itemCount;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout mFrameLayout;
        TextView mName;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_item_tip);
            mFrameLayout = itemView.findViewById(R.id.fl_main_layout);
        }
    }
}
