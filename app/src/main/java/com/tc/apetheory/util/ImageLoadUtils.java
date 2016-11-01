package com.tc.apetheory.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.apetheory.R;

/**
 * author：   tc
 * date：     2016/10/31 & 20:30
 * version    1.0
 * description
 * modify by
 */

public class ImageLoadUtils {
    public static void loadImage(Context context, String url, ImageView img) {
        Glide.with(context)
                .load(url)
                .error(R.drawable.ic_expandable)//load失敗的Drawable
                .placeholder(R.mipmap.ic_launcher)//loading時候的Drawable
                .into(img);
    }
}
