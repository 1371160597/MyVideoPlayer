package com.example.jason.myvideoplayer.videoDetail.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.videoDetail.fragment.VideoDetailFragment;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType1;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType2;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType3;
import com.example.jason.myvideoplayer.videoDetail.recyclerModel.RecyclerType4;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/16
 */

public class VideoMessageRecyclerAdapter extends BaseMultiItemQuickAdapter<VideoMessageRecyclerAdapter.VideoMessage,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public VideoMessageRecyclerAdapter(List<VideoMessage> data) {
        super(data);
        addItemType(VideoMessage.TYPE1, R.layout.video_message_recycler_type1);
        addItemType(VideoMessage.TYPE2, R.layout.video_message_recycler_type2);
        addItemType(VideoMessage.TYPE3, R.layout.video_message_recycler_type3);
        addItemType(VideoMessage.TYPE4, R.layout.video_message_recycler_type4);
        addItemType(VideoMessage.TYPE5, R.layout.video_message_recycler_type3);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoMessageRecyclerAdapter.VideoMessage item) {
        switch (helper.getItemViewType()) {
            case VideoMessage.TYPE1:
                dealWithType1Things(helper, item);
                break;
            case VideoMessage.TYPE2:
                dealWithType2Things(helper, item);
                break;
            case VideoMessage.TYPE3:
                dealWithType3Things(helper, item);
                break;
            case VideoMessage.TYPE4:
                dealWithType4Things(helper, item);
                break;
            case VideoMessage.TYPE5:
                dealWithType5Things(helper, item);
                break;
        }
    }

    public static class VideoMessage implements MultiItemEntity {
        public static final int TYPE1 = 0;//标题+详细介绍+评分
        public static final int TYPE2 = 1;//标题+圆形图片+关注量
        public static final int TYPE3 = 2;//正片类型1：集数
        public static final int TYPE4 = 3;//花絮片段
        public static final int TYPE5 = 4;//正片类型2：集数
        public static final int TYPE6 = 5;//
        public int currentType;
        private RecyclerType1 recyclerType1;
        private RecyclerType2 recyclerType2;
        private List<RecyclerType3> recyclerType3;
        private List<RecyclerType4> recyclerType4;
        private List<RecyclerType3> recyclerType5;

        public VideoMessage(RecyclerType1 recyclerType1,int type) {
            this.recyclerType1 = recyclerType1;
            currentType = type;
        }

        public VideoMessage(RecyclerType2 recyclerType2, int type) {
            this.recyclerType2 = recyclerType2;
            currentType = type;
        }

        public VideoMessage(List<RecyclerType3> recyclerType3, int type,int flag) {
            if (flag == 0) {
                this.recyclerType3 = recyclerType3;
            } else {
                this.recyclerType5 = recyclerType3;
            }
            currentType = type;
        }

        public VideoMessage(List<RecyclerType4> recyclerType4, int type) {
            this.recyclerType4 = recyclerType4;
            currentType = type;
        }

        @Override
        public int getItemType() {
            return currentType;
        }
    }

    private void dealWithType1Things(BaseViewHolder helper, VideoMessage item) {
        helper.setText(R.id.video_message_type1_linear_title, item.recyclerType1.getTitle());
        helper.setText(R.id.video_message_type1_linear_scores, item.recyclerType1.getScores());
        helper.setText(R.id.video_message_type1_linear_number, item.recyclerType1.getNumber());
        helper.setText(R.id.video_message_type1_linear_update, item.recyclerType1.getUpdate());

        helper.setText(R.id.video_message_type1_linear_hidden_text1, item.recyclerType1.getText1());
        helper.setText(R.id.video_message_type1_linear_hidden_text2, item.recyclerType1.getText2());
        helper.setText(R.id.video_message_type1_linear_hidden_text3, item.recyclerType1.getText3());
        helper.setText(R.id.video_message_type1_linear_hidden_text4, item.recyclerType1.getText4());
        helper.setText(R.id.video_message_type1_linear_hidden_text5, item.recyclerType1.getText5());
        helper.setText(R.id.video_message_type1_linear_hidden_text6, item.recyclerType1.getText6());
        helper.setText(R.id.video_message_type1_linear_hidden_text7, item.recyclerType1.getText7());
        final LinearLayout hidden = helper.getView(R.id.video_message_type1_linear_hidden);
        helper.getView(R.id.video_message_type1_linear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hidden.getVisibility() == View.VISIBLE) {
                    hidden.setVisibility(View.GONE);
                } else {
                    hidden.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void dealWithType2Things(BaseViewHolder helper, VideoMessage item) {
        helper.setText(R.id.video_message_type2_name, item.recyclerType2.getTitle());
        helper.setText(R.id.video_message_type2_number, item.recyclerType2.getCount());
        ImageView image = helper.getView(R.id.video_message_type2_circle_image);
        Glide.with(mContext).load(item.recyclerType2.getImageUrl()).into(image);
    }

    private void dealWithType3Things(BaseViewHolder helper, VideoMessage item) {
        helper.setText(R.id.video_message_type3_title, item.recyclerType3.get(0).getTitle());
        RecyclerView recyclerViewType3 = helper.getView(R.id.video_message_type3_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewType3.setLayoutManager(layoutManager);
        VideoMessageRecyclerType3Adapter type3Adapter = new VideoMessageRecyclerType3Adapter(item.recyclerType3);
        recyclerViewType3.setAdapter(type3Adapter);
    }

    private void dealWithType4Things(BaseViewHolder helper, VideoMessage item) {
        helper.setText(R.id.video_message_type4_title, item.recyclerType4.get(0).getTitle());
        RecyclerView recyclerViewType4 = helper.getView(R.id.video_message_type4_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewType4.setLayoutManager(layoutManager);
        VideoMessageRecyclerType4Adapter type4Adapter = new VideoMessageRecyclerType4Adapter(item.recyclerType4, mContext);
        recyclerViewType4.setAdapter(type4Adapter);
    }

    private void dealWithType5Things(BaseViewHolder helper, VideoMessage item) {
        helper.setText(R.id.video_message_type3_title, item.recyclerType5.get(0).getTitle());
        RecyclerView recyclerViewType5 = helper.getView(R.id.video_message_type3_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewType5.setLayoutManager(layoutManager);
        VideoMessageRecyclerType5Adapter type5Adapter = new VideoMessageRecyclerType5Adapter(item.recyclerType5,mContext);
        recyclerViewType5.setAdapter(type5Adapter);
    }
}
