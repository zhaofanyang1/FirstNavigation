package com.example.mytonghang.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mytonghang.R;
import com.example.mytonghang.data.Demo;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Demo.DataBean.NewListBean> list;
    private Context context;

    public MyAdapter(List<Demo.DataBean.NewListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Demo.DataBean.NewListBean newListBean = list.get(position);
        holder.textView.setText(newListBean.getTitle());
        Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(holder.image);
        Log.d("MyAdapter", "newListBean.getImageListThumb():" + newListBean.getImageListThumb());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Demo.DataBean.NewListBean> newListBeans, int cursor) {
        if (cursor == 0) {
            list.clear();
        }
        list.addAll(newListBeans);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
