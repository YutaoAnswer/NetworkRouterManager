package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;

public class IntranetResourceAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private ChannelAdapter.OnItemClickListener mOnItemClickListener;

    public IntranetResourceAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_host_channel, parent, false);
        return new HostViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HostViewHolder extends RecyclerView.ViewHolder {

        TextView textview;

        HostViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
        }
    }
}
