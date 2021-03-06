package com.tc.apetheory.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.tc.apetheory.R;
import com.tc.apetheory.presenter.BasePresenter;
import com.tc.apetheory.view.fragment.HomeFragment;
import com.tc.apetheory.view.fragment.UserCenterFragment;
import com.tomtop.ttcom.view.fragment.BaseFragment;
import com.tomtop.ttutil.log.LogUtil;

/**
 * author：   tc
 * date：     2016/10/23 & 20:28
 * version    1.0
 * description 首页
 * modify by
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private LinearLayout mLlSmart;
    private LinearLayout mLlCommunity;
    private LinearLayout mLlCate;
    private LinearLayout mLlUserCenter;
    BaseFragment[] mFragments = new BaseFragment[4];

    private int mCurrentShowIndex = 0;//当前显示页面的索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = UserCenterFragment.newInstance();
            mFragments[2] = UserCenterFragment.newInstance();
            mFragments[3] = UserCenterFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, mCurrentShowIndex,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些)
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(UserCenterFragment.class);
            mFragments[2] = findFragment(UserCenterFragment.class);
            mFragments[3] = findFragment(UserCenterFragment.class);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void obtainData() {

    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        mLlSmart = (LinearLayout) findViewById(R.id.ll_home_smart);
        mLlCommunity = (LinearLayout) findViewById(R.id.ll_home_community);
        mLlCate = (LinearLayout) findViewById(R.id.ll_home_cate);
        mLlUserCenter = (LinearLayout) findViewById(R.id.ll_home_user_center);
    }

    @Override
    protected void initEvent() {
        mLlSmart.setOnClickListener(this);
        mLlCommunity.setOnClickListener(this);
        mLlCate.setOnClickListener(this);
        mLlUserCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LogUtil.e(TAG, "onClick: ");
        switch (v.getId()) {
            case R.id.ll_home_smart:
                showFragment(0);
                break;
            case R.id.ll_home_community:
                showFragment(1);
                break;
            case R.id.ll_home_cate:
                showFragment(2);
                break;
            case R.id.ll_home_user_center:
                showFragment(3);
                break;
            default:
                break;
        }
    }

    private void showFragment(int index) {
        showHideFragment(mFragments[index], mFragments[mCurrentShowIndex]);
        mCurrentShowIndex = index;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_BACK: {
                finish();
            }
            return true;
            default:
                return super.onKeyDown(keyCode, event);

        }

    }
}
