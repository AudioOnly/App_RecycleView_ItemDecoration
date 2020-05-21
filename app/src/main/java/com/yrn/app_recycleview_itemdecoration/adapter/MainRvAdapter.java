package com.yrn.app_recycleview_itemdecoration.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yrn.app_recycleview_itemdecoration.R;

import java.util.List;

/**
 * App_RecycleView_ItemDecoration
 * <p>
 * Created by Xinhoo on 2020/5/21
 * Describe:
 */
public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.MainRvViewHolder> {

    private List<String> mData;

    public MainRvAdapter(List<String> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MainRvAdapter.MainRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_main_rv_layout, parent, false);
        return new MainRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRvAdapter.MainRvViewHolder holder, int position) {
        String data = mData.get(position);
        holder.tv.setText(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MainRvViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MainRvViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView2);
        }
    }
}

