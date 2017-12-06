package com.example.jason.myvideoplayer.videoDetail.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType3;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/20
 * 创建时间：15:37
 */

public class VideoMessageRecyclerType3Adapter extends RecyclerView.Adapter<VideoMessageRecyclerType3Adapter.ViewHolder> {

    private List<RecyclerType3> type3List;

    public VideoMessageRecyclerType3Adapter(List<RecyclerType3> type3List) {
        this.type3List = type3List;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textFont;
        TextView textTitle;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
//            cardView = (CardView) itemView;
            textFont = (TextView) itemView.findViewById(R.id.video_message_type3_adapter_font);
            textTitle = (TextView) itemView.findViewById(R.id.video_message_type3_adapter_title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_message_recycler_type3_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerType3 type3 = type3List.get(position);
        if (position < type3List.size() - 1) {
            holder.textTitle.setText(type3.getVideoIndex());
            if (!TextUtils.isEmpty(type3.getColor())) {
                holder.textFont.setText(type3.getFont());
                holder.textFont.setBackgroundColor(Color.parseColor(type3.getColor()));
                holder.textFont.setVisibility(View.VISIBLE);
            } else {
                holder.textFont.setVisibility(View.GONE);
            }
        }
//        } else if (position == type3List.size() - 1){
//            holder.textTitle.setText(type3.getName());
//            if (!TextUtils.isEmpty(type3.getColor())) {
//                holder.textFont.setText(type3.getFont());
//                holder.textFont.setBackgroundColor(Color.parseColor(type3.getColor()));
//                holder.textFont.setVisibility(View.VISIBLE);
//            } else {
//                holder.textFont.setVisibility(View.GONE);
//            }
//        }

    }

    @Override
    public int getItemCount() {
        //减去最后一个推荐的
        return type3List.size() - 1;
    }
}
