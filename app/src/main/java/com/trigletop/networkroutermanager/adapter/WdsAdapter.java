package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.trigletop.networkroutermanager.R;

import java.util.List;

import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSScanInfo;

public class WdsAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private List<WDSScanInfo> deviceList;

    public List<WDSScanInfo> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<WDSScanInfo> deviceList) {
        this.deviceList = deviceList;
    }

    public WdsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WdsViewHolder(View.inflate(mContext, R.layout.item_rcy_wds, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final WdsViewHolder viewHolder = (WdsViewHolder) holder;
        if (deviceList != null && deviceList.size() > 0 && position < deviceList.size()) {
            WDSScanInfo wdsScanInfo = deviceList.get(position);
            viewHolder.tvWirelessName.setText(wdsScanInfo.getSsid());
            float level = (float) (-1 * (wdsScanInfo.getNoise() - wdsScanInfo.getSignal()) / 5.00);
            if (level < 1) {
                viewHolder.ivStrength.setImageResource(R.mipmap.single_one);
            } else if (level >= 1 && level < 2) {
                viewHolder.ivStrength.setImageResource(R.mipmap.single_two);
            } else if (level >= 2 && level < 3) {
                viewHolder.ivStrength.setImageResource(R.mipmap.single_three);
            } else if (level >= 3 && level < 4) {
                viewHolder.ivStrength.setImageResource(R.mipmap.single_four);
            } else {
                viewHolder.ivStrength.setImageResource(R.mipmap.single_five);
            }
            viewHolder.tvEncipheredMessage.setText(wdsScanInfo.getEncryption().getDescription());
            viewHolder.rbChoose.setOnClickListener(v -> {
                NiftyDialogBuilder niftyDialogBuilder = new NiftyDialogBuilder(mContext);
                if (wdsScanInfo.getEncryption().isEnabled()) {//可以直连
                    niftyDialogBuilder
                            .setCustomView(R.layout.custom_view_wds, mContext)
                            .withTitle("输入主路由器的无线密码");
                    EditText etPassword = niftyDialogBuilder.findViewById(R.id.et_password);
                    niftyDialogBuilder.withButton1Text("下一步")
                            .setButton1Click(v1 -> {
                                if (!etPassword.getText().toString().isEmpty()) {
                                    NiftyDialogBuilder niftyDialogBuilder1 = new NiftyDialogBuilder(mContext);
                                    niftyDialogBuilder1
                                            .withTitle("请设置本路由器的无线参数")
                                            .withButton1Text("上一步")
                                            .withButton2Text("下一步");
                                    niftyDialogBuilder1
                                            .setButton1Click(v2 -> {

                                            })
                                            .setButton2Click(v22 -> {

                                            });
                                    niftyDialogBuilder1.show();
                                }
                            });
                } else {
                    niftyDialogBuilder
                            .withTitle("路由器没有加密并确认连接.请点击下一步")
                            .withButton1Text("下一步")
                            .setButton1Click(v12 -> {

                            });
                }
                niftyDialogBuilder.show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    private class WdsViewHolder extends RecyclerView.ViewHolder {

        TextView tvWirelessName;
        ImageView ivStrength;
        TextView tvEncipheredMessage;
        RadioButton rbChoose;

        WdsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWirelessName = itemView.findViewById(R.id.tv_wireless_name);
            ivStrength = itemView.findViewById(R.id.iv_strength);
            tvEncipheredMessage = itemView.findViewById(R.id.tv_enciphered_message);
            rbChoose = itemView.findViewById(R.id.rb_choose);
        }

    }


}
