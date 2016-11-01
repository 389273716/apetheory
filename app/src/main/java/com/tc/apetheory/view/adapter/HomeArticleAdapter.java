package com.tc.apetheory.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tc.apetheory.R;
import com.tc.apetheory.entity.entities.HomeArticle;
import com.tc.apetheory.util.ImageLoadUtils;
import com.tomtop.ttcom.view.adapter.RecyclerBaseAdapter;
import com.tomtop.ttcom.view.adapter.ViewHolder;

import java.util.List;


/**
 * @author tc
 * @version 1.0
 * @date 2016/4/4 13:31
 */
public class HomeArticleAdapter extends RecyclerBaseAdapter<HomeArticle> {
    private LayoutInflater mLayoutInflater;

    public HomeArticleAdapter(@NonNull Context context, @NonNull List<HomeArticle> mDataList) {
        super(context, mDataList);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, HomeArticle homeArticle, int position) {
        TextView tvTypeName = holder.getView(com.tc.apetheory.R.id.tv_home_type_name);
        TextView tvTime = holder.getView(R.id.tv_home_time);
        TextView tvTitle = holder.getView(R.id.tv_home_title);
        TextView tvDigest = holder.getView(R.id.tv_home_digest);
        ImageView img = holder.getView(R.id.iv_home_image);

        if (homeArticle != null) {
            tvTypeName.setText(String.format("From %s", homeArticle.getName()));
            tvTime.setText(homeArticle.getInputDate());
            tvTitle.setText(homeArticle.getTitle());
            tvDigest.setText(homeArticle.getDigest());
            ImageLoadUtils.loadImage(getContext(),homeArticle.getImgUrl(),img);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_home_article, parent, false);
        return new ViewHolder(view);
    }
}
