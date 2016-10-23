package com.tc.apetheory.view.activity;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tc.apetheory.R;
import com.tc.apetheory.presenter.BasePresenter;

/**
 * author：   tc
 * date：     2016/9/21 & 20:23
 * version    1.0
 * description
 * modify by
 */

public class TestActivity extends BaseActivity {
    private static final String TAG = TestActivity.class.getSimpleName();
    private String url = "http://i.imgur.com/DvpvklR.png";
    private ImageView mIvImg;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void obtainData() {
        Glide.with(this)
                .load(url)
                .error(R.drawable.ic_expandable)//load失敗的Drawable
                .placeholder(R.mipmap.ic_launcher)//loading時候的Drawable
                .into(mIvImg);
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void initView() {
        mIvImg = (ImageView) findViewById(R.id.iv_test_img);

    }

    @Override
    protected void initEvent() {

    }

}
