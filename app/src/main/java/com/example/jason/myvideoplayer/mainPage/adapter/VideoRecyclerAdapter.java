package com.example.jason.myvideoplayer.mainPage.adapter;


import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.jason.myvideoplayer.R;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType3Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType2Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType4Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType5Photo;
import com.example.jason.myvideoplayer.mainPage.recyclerModel.RecyclerType6Photo;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/7
 */

public class VideoRecyclerAdapter extends BaseMultiItemQuickAdapter<VideoRecyclerAdapter.VideoRecycler,BaseViewHolder>{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public VideoRecyclerAdapter(List<VideoRecycler> data) {
        super(data);
        addItemType(VideoRecycler.TYPE1, R.layout.video_recycler_item1);
        addItemType(VideoRecycler.TYPE2, R.layout.video_recycler_item2);
        addItemType(VideoRecycler.TYPE3, R.layout.video_recycler_item3);
        addItemType(VideoRecycler.TYPE4, R.layout.video_recycler_item4);
        addItemType(VideoRecycler.TYPE5, R.layout.video_recycler_item5);
        addItemType(VideoRecycler.TYPE6, R.layout.video_recycler_item6);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoRecycler item) {
        switch (item.getItemType()) {
            case VideoRecycler.TYPE1:
                TextView title = helper.getView(R.id.type1_title);
//                Typeface typeface = Typeface.create("黑体", Typeface.BOLD);
//                title.getPaint().setTypeface(typeface);
                if (item.title.equals("头条来了")) {
                    helper.setVisible(R.id.type1_title_icon1, true);
                    helper.setVisible(R.id.type1_title_icon2, true);
                } else if (item.title.equals("今日热门")) {
                    helper.setVisible(R.id.type1_title_icon1, false);
                    helper.setVisible(R.id.type1_title_icon2, true);
                } else {
                    helper.setVisible(R.id.type1_title_icon1, false);
                    helper.setVisible(R.id.type1_title_icon2, false);
                }

                title.setText(item.title);
                break;
            case VideoRecycler.TYPE2:
                ImageView image = helper.getView(R.id.video_recycler_item_photo);
                Glide.with(mContext).load(item.typePhoto.getImageId()).into(image);
                helper.setText(R.id.video_recycler_item_right_corner, item.typePhoto.getRightCorner());
                if (item.typePhoto.getRightCorner().equals("特辑")) {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner, Color.parseColor("#04911e"));
                }else if (item.typePhoto.getRightCorner().equals("自制")) {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner,Color.parseColor("#FF4500"));
                }else if (item.typePhoto.getRightCorner().equals("VIP免费")) {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner,Color.parseColor("#FFD700"));
                } else if (item.typePhoto.getRightCorner().equals("预告")) {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner, Color.parseColor("#20B2AA"));
                } else if (item.typePhoto.getRightCorner().equals("")) {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner,Color.parseColor("#00000000"));
                }else {
                    helper.setBackgroundColor(R.id.video_recycler_item_right_corner,Color.parseColor("#FF4500"));
                }

                if (!TextUtils.isEmpty(item.typePhoto.getUpdateInfo())) {
                    helper.setText(R.id.video_recycler_item_right_text, item.typePhoto.getUpdateInfo());
                    helper.setBackgroundRes(R.id.video_recycler_item_right_text, R.drawable.video_recycler_item_update_info);
                }
                helper.setVisible(R.id.video_recycler_item_right_text,!TextUtils.isEmpty(item.typePhoto.getUpdateInfo()));//不为空时，visible为true，显示数据，为空时，visible为false

                helper.setText(R.id.video_recycler_item_title_big, item.typePhoto.getName());
                helper.setText(R.id.video_recycler_item_title_small, item.typePhoto.getSubName());
                break;
            case VideoRecycler.TYPE3:
                ImageView image2 = helper.getView(R.id.video_recycler_item2_photo);
                Glide.with(mContext).load(item.type2Photo.getImageId()).into(image2);
                helper.setText(R.id.video_recycler_item2_right_corner, item.type2Photo.getRightCorner());
                if (item.type2Photo.getRightCorner().equals("特辑")) {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner, Color.parseColor("#04911e"));
                }else if (item.type2Photo.getRightCorner().equals("自制")) {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner,Color.parseColor("#FF4500"));
                }else if (item.type2Photo.getRightCorner().equals("VIP免费")) {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner,Color.parseColor("#FFD700"));
                } else if (item.type2Photo.getRightCorner().equals("预告")) {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner, Color.parseColor("#20B2AA"));
                } else if (item.type2Photo.getRightCorner().equals("")) {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner,Color.parseColor("#00000000"));
                }else {
                    helper.setBackgroundColor(R.id.video_recycler_item2_right_corner,Color.parseColor("#FF4500"));
                }
                if (!TextUtils.isEmpty(item.type2Photo.getUpdateInfo())) {
                    helper.setText(R.id.video_recycler_item2_right_text, item.type2Photo.getUpdateInfo());
                    helper.setBackgroundRes(R.id.video_recycler_item2_right_text, R.drawable.video_recycler_item_update_info);
                }
                helper.setVisible(R.id.video_recycler_item2_right_text,!TextUtils.isEmpty(item.type2Photo.getUpdateInfo()));
                helper.setText(R.id.video_recycler_item2_title_big, item.type2Photo.getName());
                break;
            case VideoRecycler.TYPE4:
                ImageView image4 = helper.getView(R.id.video_recycler_item4_circle_image);
                Glide.with(mContext).load(item.type4Photo.getImageId()).into(image4);
                helper.setText(R.id.video_recycler_item4_title, item.type4Photo.getName());
                helper.setText(R.id.video_recycler_item4_content, item.type4Photo.getSubName());
                break;
            case VideoRecycler.TYPE5:
                ImageView image5 = helper.getView(R.id.video_recycler_item5_photo);
                Glide.with(mContext).load(item.type5Photo.getImageId()).into(image5);
                if (!TextUtils.isEmpty(item.type5Photo.getUpdateInfo())) {
                    helper.setText(R.id.video_recycler_item5_right_text, item.type5Photo.getUpdateInfo());
                    helper.setBackgroundRes(R.id.video_recycler_item5_right_text, R.drawable.video_recycler_item_update_info);
                }
                helper.setVisible(R.id.video_recycler_item5_right_text,!TextUtils.isEmpty(item.type5Photo.getUpdateInfo()));
                helper.setText(R.id.video_recycler_item5_title_big, item.type5Photo.getName());
                helper.setText(R.id.video_recycler_item5_title_small, item.type5Photo.getSubName());
                break;
            case VideoRecycler.TYPE6:
                ImageView image6 = helper.getView(R.id.video_recycler_item6_photo);
                Glide.with(mContext).load(item.type6Photo.getImageId()).into(image6);
                helper.setText(R.id.video_recycler_item6_title_big, item.type6Photo.getName());
                break;
        }

    }

    public static class VideoRecycler implements MultiItemEntity {
        public static final int TYPE1 = 0;//标题类型
        public static final int TYPE2 = 1;//内容框  多图片+多标题
        public static final int TYPE3 = 2;//内容框1  1图片+1标题
        public static final int TYPE4 = 3;//标题框  标题+图片
        public static final int TYPE5 = 4;//大图框   标题+图片
        public static final int TYPE6 = 5;//新闻   标题+图片
        public int currentType;
        public String title;
        public RecyclerType2Photo typePhoto;
        public RecyclerType3Photo type2Photo;
        public RecyclerType4Photo type4Photo;
        public RecyclerType5Photo type5Photo;
        public RecyclerType6Photo type6Photo;

        public VideoRecycler(String title,int type) {
            this.title = title;
            currentType = type;
        }

        public VideoRecycler(RecyclerType2Photo typePhoto, int type) {
            this.typePhoto = typePhoto;
            this.currentType = type;
        }

        public VideoRecycler(RecyclerType3Photo type2Photo, int type) {
            this.type2Photo = type2Photo;
            this.currentType = type;
        }

        public VideoRecycler(RecyclerType4Photo type4Photo, int type) {
            this.type4Photo = type4Photo;
            this.currentType = type;
        }

        public VideoRecycler(RecyclerType5Photo type5Photo, int type) {
            this.type5Photo = type5Photo;
            this.currentType = type;
        }

        public VideoRecycler(RecyclerType6Photo type6Photo, int type) {
            this.type6Photo = type6Photo;
            this.currentType = type;
        }

        @Override
        public int getItemType() {
            return currentType;
        }
    }
}
