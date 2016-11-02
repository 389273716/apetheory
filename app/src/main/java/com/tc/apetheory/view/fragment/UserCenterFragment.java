package com.tc.apetheory.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tc.apetheory.R;
import com.tc.apetheory.presenter.BasePresenter;

/**
 * author：   tc
 * date：     2016/11/2 & 20:45
 * version    1.0
 * description
 * modify by
 */

public class UserCenterFragment extends BaseViewFragment {
    public static UserCenterFragment newInstance() {
        UserCenterFragment fragment = new UserCenterFragment();
        return fragment;
    }
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected View setContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle
            bundle) {
        return layoutInflater.inflate(R.layout.fragment_user_center, viewGroup, false);
    }

    @Override
    public void obtainData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void bindData() {

    }
}
