package com.jiyun.firstnavigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.firstnavigation.R;
import com.jiyun.firstnavigation.beans.DownListNews;

import java.util.List;
import java.util.Timer;

public class DownListNewsAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private List<DownListNews.NewListBean> list;
    private Context context;

    public DownListNewsAdapter(List<DownListNews.NewListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return 1;
        }
        if (position % 4 == 1) {
            return 2;
        }
        if (position % 4 == 2) {
            return 3;
        } else {
            return 4;
        }
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item1, null);
            viewHolder = new ViewHolderA(inflate);
        }
        if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item2, null);
            viewHolder = new ViewHolderB(inflate);
        }
        if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item3, null);
            viewHolder = new ViewHolderC(inflate);
        }
        if (viewType == 4) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item4, null);
            viewHolder = new ViewHolderD(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
        DownListNews.NewListBean newListBean = list.get(position);
        if (holder instanceof ViewHolderA) {
            ((ViewHolderA) holder).title.setText(newListBean.getTitle());
            int pageviews = newListBean.getPageviews();
            ((ViewHolderA) holder).gentie.setText(pageviews + "");
            ((ViewHolderA) holder).time.setText(newListBean.getPublishTime());
        }
        if (holder instanceof ViewHolderB) {
            ((ViewHolderB) holder).title.setText(newListBean.getTitle());
            ((ViewHolderB) holder).gentie.setText(newListBean.getPageviews() + "");
            ((ViewHolderB) holder).time.setText(newListBean.getPublishTime());
            Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(((ViewHolderB) holder).image);
        }
        if (holder instanceof ViewHolderC) {
            ((ViewHolderC) holder).title.setText(newListBean.getTitle());
            ((ViewHolderC) holder).time.setText(newListBean.getPublishTime());
            ((ViewHolderC) holder).gentie.setText(newListBean.getPageviews() + "");
            Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(((ViewHolderC) holder).image1);
            Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(((ViewHolderC) holder).image2);
            Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(((ViewHolderC) holder).image3);
        }

        if (holder instanceof ViewHolderD) {
            ((ViewHolderD) holder).time.setText(newListBean.getPublishTime());
            ((ViewHolderD) holder).title.setText(newListBean.getTitle());
            ((ViewHolderD) holder).gentie.setText(newListBean.getPageviews() + "");
            Glide.with(context).load(newListBean.getImageListThumb().get(0)).into(((ViewHolderD) holder).image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderA extends XRecyclerView.ViewHolder {
        TextView title, gentie, time;
        ImageView zhiding;

        public ViewHolderA(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_title);
            time = itemView.findViewById(R.id.time);
            zhiding = itemView.findViewById(R.id.id_zhiding);
            gentie = itemView.findViewById(R.id.gentie);
        }
    }

    class ViewHolderB extends XRecyclerView.ViewHolder {
        TextView title, gentie, time;
        ImageView image, zhiding, guanbi;

        public ViewHolderB(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_title);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.id_item2);
            zhiding = itemView.findViewById(R.id.id_zhiding);
            gentie = itemView.findViewById(R.id.gentie);
            guanbi = itemView.findViewById(R.id.guanbi);
        }
    }

    class ViewHolderC extends XRecyclerView.ViewHolder {
        TextView title, gentie, time;
        ImageView image1, image2, image3, zhiding, guanbi;

        public ViewHolderC(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_title);
            time = itemView.findViewById(R.id.time);
            image1 = itemView.findViewById(R.id.id_item3_1);
            image2 = itemView.findViewById(R.id.id_item3_2);
            image3 = itemView.findViewById(R.id.id_item3_3);
            zhiding = itemView.findViewById(R.id.id_zhiding);
            gentie = itemView.findViewById(R.id.gentie);
            guanbi = itemView.findViewById(R.id.guanbi);
        }
    }

    class ViewHolderD extends XRecyclerView.ViewHolder {
        TextView title, gentie, time;
        ImageView image, zhiding, guanbi;

        public ViewHolderD(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_title);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.item4);
            zhiding = itemView.findViewById(R.id.id_zhiding);
            gentie = itemView.findViewById(R.id.gentie);
            guanbi = itemView.findViewById(R.id.guanbi);
        }
    }

}
