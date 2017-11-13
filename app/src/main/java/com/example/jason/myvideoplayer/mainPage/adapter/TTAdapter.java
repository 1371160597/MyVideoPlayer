package com.example.jason.myvideoplayer.mainPage.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.jason.myvideoplayer.R;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/11/7
 */

public class TTAdapter extends BaseMultiItemQuickAdapter<TTAdapter.MuEntity,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TTAdapter(List<MuEntity> data) {
        super(data);
        addItemType(MuEntity.TYPE1, R.layout.item_1);
        addItemType(MuEntity.TYPE2, R.layout.item_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MuEntity item) {
//        helper.getView()
        switch (item.getItemType()){
            case MuEntity.TYPE1:
                helper.setText(R.id.tv_title,item.title);
                break;
            case MuEntity.TYPE2:
                ImageView ivPic = helper.getView(R.id.iv_pic);
                ivPic.setImageResource(item.picResId);
                break;
        }
    }

    public static class MuEntity implements MultiItemEntity{

        public static final int TYPE1=1;
        public static final int TYPE2=2;
        private int itemType;
        private String title;
        private int picResId;
        public MuEntity(String title){
            this.title=title;
            itemType=TYPE1;
        }

        public MuEntity(int resId,int type){
            this.picResId=resId;
            itemType=type;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public String getTitle() {
            return title;
        }

        public int getPicResId() {
            return picResId;
        }
    }
}
