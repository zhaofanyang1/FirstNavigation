package com.jiyun.firstnavigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.beans.MyChannel;
import com.jiyun.firstnavigation.dao.MyChannelDao;

import java.util.List;

public class MyChannelsAdapter1 extends XRecyclerView.Adapter<MyChannelsAdapter1.ViewHolder> {
    private List<MyChannel> list;
    private Context context;

    public MyChannelsAdapter1(List<MyChannel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyChannelsAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mychannels1, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyChannelsAdapter1.ViewHolder holder, int position) {
        holder.item1.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item1;

        public ViewHolder(View itemView) {
            super(itemView);
            item1 = itemView.findViewById(R.id.item1);
        }
    }
}
