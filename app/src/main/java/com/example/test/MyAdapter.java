package com.example.test;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<MyFood.Dat, BaseViewHolder> {

    public MyAdapter(List<MyFood.Dat> data) {
        super(R.layout.food_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, MyFood.Dat myFood) {
        Glide.with(getContext()).load(myFood.getCover()).into((ImageView) baseViewHolder.getView(R.id.item_img));

    }
}
