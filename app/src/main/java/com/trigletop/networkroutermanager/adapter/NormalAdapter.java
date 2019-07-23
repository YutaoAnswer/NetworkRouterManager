package com.trigletop.networkroutermanager.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.trigletop.networkroutermanager.R;
import com.trigletop.networkroutermanager.utils.ContantUtil;


public class NormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public NormalAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(mContext, R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;
        viewHolder.mName.setText(ContantUtil.TEST_DATAS[position]);
        GradientDrawable drawable =(GradientDrawable)viewHolder.mFrameLayout.getBackground();
        drawable.setColor(ContextCompat.getColor(mContext, ContantUtil.getRandColor()));
    }

    @Override
    public int getItemCount() {
        return ContantUtil.TEST_DATAS.length;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout mFrameLayout;
        TextView mName;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_item_tip);
            mFrameLayout = (FrameLayout) itemView.findViewById(R.id.fl_main_layout);
        }
    }
}
