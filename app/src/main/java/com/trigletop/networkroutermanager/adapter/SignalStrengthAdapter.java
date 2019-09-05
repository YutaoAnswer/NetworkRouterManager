package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.Bean.Data;
import com.trigletop.networkroutermanager.R;

public class SignalStrengthAdapter extends RecyclerView.Adapter<SignalStrengthAdapter.HostViewHolder> {

    private Context mContext;

    private ChannelAdapter.OnItemClickListener mOnItemClickListener;

    public SignalStrengthAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public HostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_host_channel, parent, false);
        return new HostViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HostViewHolder holder, int position) {
        holder.textview.setText(Data.host_channel_data[position]);
        // item click
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(view1 -> mOnItemClickListener.onItemClick(holder.itemView, position));
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemClickListener(ChannelAdapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    class HostViewHolder extends RecyclerView.ViewHolder {

        TextView textview;

        HostViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
        }
    }
}
