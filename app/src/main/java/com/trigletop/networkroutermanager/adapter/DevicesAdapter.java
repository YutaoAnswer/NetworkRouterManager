package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.ContantUtil;

import java.util.List;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DevicesAdapter.class.getSimpleName();

    private Context mContext;
    private String mParam;
    private LocalApi mLocalApi;

    private List<Device> deviceList;

    public DevicesAdapter(Context context) {
        mContext = context;
    }

    public DevicesAdapter(Context context, String param, LocalApi localApi) {
        mContext = context;
        mParam = param;
        mLocalApi = localApi;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // TODO: 19-7-30 如果deviceList为空怎么办，则不会设置任何值 为空处理
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        Log.d(TAG, "onBindViewHolder: position" + position);
        if (deviceList != null && deviceList.size() > 0 && position < deviceList.size()) {
            Device device = deviceList.get(position);
            switch (mParam) {
                case "Connected":
                    if (device.getAuthority().getInternet() == 1) {
                        if (device.getNickname().equals("")) {
                            viewHolder.mName.setText(mContext.getString(R.string.anonymous_host));
                        } else {
                            viewHolder.mName.setText(deviceList.get(position).getNickname());
                        }
                        viewHolder.mDownload.setText(String.valueOf(device.getSpeed().getDownspeed()));
                        viewHolder.mUpload.setText(String.valueOf(device.getSpeed().getUpspeed()));
                        GradientDrawable drawable = (GradientDrawable) viewHolder.mFrameLayout.getBackground();
                        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));
                    }
                    break;
                case "Forbidden":
                    if (device.getAuthority().getInternet() == 0) {
                        viewHolder.mFrameLayout_Left.setVisibility(View.GONE);
                        if (device.getNickname().equals("")) {
                            viewHolder.mName.setText(mContext.getString(R.string.anonymous_host));
                        } else {
                            viewHolder.mName.setText(deviceList.get(position).getNickname());
                        }
                        GradientDrawable drawable = (GradientDrawable) viewHolder.mFrameLayout.getBackground();
                        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));
                    }
                    break;
                default:
                    break;
            }
//            if (device.getAuthority().getLan() == 1) {
//                if (device.getNickname().equals("")) {
//                    viewHolder.mName.setText(mContext.getString(R.string.anonymous_host));
//                } else {
//                    viewHolder.mName.setText(deviceList.get(position).getNickname());
//                }
//                viewHolder.mDownload.setText(String.valueOf(device.getSpeed().getDownspeed()));
//                viewHolder.mUpload.setText(String.valueOf(device.getSpeed().getUpspeed()));
//            } else if (device.getAuthority().getLan() == -1) {
//                viewHolder.mFrameLayout_Left.setVisibility(View.GONE);
//                if (device.getNickname().equals("")) {
//                    viewHolder.mName.setText(mContext.getString(R.string.anonymous_host));
//                } else {
//                    viewHolder.mName.setText(deviceList.get(position).getNickname());
//                }
////                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewHolder.mFrameLayout_Rigth.getLayoutParams();
////                viewHolder.mFrameLayout_Rigth.setLayoutParams(params);
////                viewHolder.mForbidden.setLayoutParams(new LinearLayout.LayoutParams());
//            }
        }
    }

    @Override
    public int getItemCount() {
        // TODO: 19-8-1 优化点
        int itemCount = 0;
        if (deviceList != null) {
            itemCount = deviceList.size();
        }
        return itemCount;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout mFrameLayout;
        TextView mName;
        TextView mDownload;
        TextView mUpload;
        LinearLayout mFrameLayout_Left;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_item_tip);
            mDownload = itemView.findViewById(R.id.tv_download);
            mUpload = itemView.findViewById(R.id.tv_upload);
            mFrameLayout = itemView.findViewById(R.id.fl_main_layout);
            mFrameLayout_Left = itemView.findViewById(R.id.ll_container);
        }
    }
}
