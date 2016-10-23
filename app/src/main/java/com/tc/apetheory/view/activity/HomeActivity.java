package com.tc.apetheory.view.activity;

import com.tc.apetheory.R;
import com.tc.apetheory.databinding.ActivityHomeBinding;
import com.tc.apetheory.presenter.BasePresenter;

/**
 * author：   tc
 * date：     2016/10/23 & 20:28
 * version    1.0
 * description 首页
 * modify by
 */

public class HomeActivity extends BaseActivity {
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void obtainData() {
        ActivityHomeBinding binding=new ActivityHomeBinding();
        binding.getArticle();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }
}
