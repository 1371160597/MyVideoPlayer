package com.example.jason.myvideoplayer.videoDetail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType3;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/29
 * 创建时间：15:16
 */

public class VideoMessageRecyclerType5Adapter extends RecyclerView.Adapter<VideoMessageRecyclerType5Adapter.ViewHolder> {

    private Context context;
    private List<RecyclerType3> type5List;

    public VideoMessageRecyclerType5Adapter(List<RecyclerType3> type5List, Context context) {
        this.type5List = type5List;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textCorner;
        TextView textRight;
        TextView textTitle;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textCorner = (TextView) itemView.findViewById(R.id.video_message_type5_right_corner);
            textTitle = (TextView) itemView.findViewById(R.id.video_message_type5_title);
            textRight = (TextView) itemView.findViewById(R.id.video_message_type5_right_text);
            image = (ImageView) itemView.findViewById(R.id.video_message_type5_image);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_message_recycler_type5_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerType3 type5 = type5List.get(position);
        if (position < type5List.size() - 1) {
            Glide.with(context).load(type5.getImageUrl()).into(holder.image);
            holder.textTitle.setText(type5.getName());
            if (!TextUtils.isEmpty(type5.getColor())) {
                holder.textCorner.setText(type5.getFont());
                holder.textCorner.setBackgroundColor(Color.parseColor(type5.getColor()));
                holder.textCorner.setVisibility(View.VISIBLE);
            } else {
                holder.textCorner.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(type5.getInfo())) {
                holder.textRight.setText(type5.getInfo());
                holder.textRight.setVisibility(View.VISIBLE);
            } else {
                holder.textRight.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        //减去最后一个推荐的
        return type5List.size() - 1;
    }
}
