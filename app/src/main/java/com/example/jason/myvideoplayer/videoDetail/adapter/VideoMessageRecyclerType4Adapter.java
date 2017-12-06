package com.example.jason.myvideoplayer.videoDetail.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType4;

import java.util.List;
import java.util.zip.Inflater;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/21
 * 创建时间：17:24
 */

public class VideoMessageRecyclerType4Adapter extends RecyclerView.Adapter<VideoMessageRecyclerType4Adapter.ViewHolder>{

    private Context context;
    private List<RecyclerType4> type4List;

    public VideoMessageRecyclerType4Adapter(List<RecyclerType4> type4List, Context context) {
        this.type4List = type4List;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView desc;
        TextView title;
        TextView info;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.video_message_type4_adapter_image);
            desc = (TextView) itemView.findViewById(R.id.video_message_type4_adapter_desc);
            title = (TextView) itemView.findViewById(R.id.video_message_type4_adapter_title);
            info = (TextView) itemView.findViewById(R.id.video_message_type4_adapter_info);
        }
    }
    @Override
    public VideoMessageRecyclerType4Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_message_recycler_type4_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoMessageRecyclerType4Adapter.ViewHolder holder, int position) {
        RecyclerType4 type4 = type4List.get(position);
        Glide.with(context).load(type4.getImage()).into(holder.image);
        holder.desc.setText(type4.getDesc());
        holder.title.setText(type4.getName());
        holder.info.setText(type4.getInfo());
    }

    @Override
    public int getItemCount() {
        return type4List.size();
    }
}
