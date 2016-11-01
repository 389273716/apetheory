/**
 * Copyright 2015 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tc.apetheory.widgets.refreshholder;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.tc.apetheory.R;

import static android.view.View.inflate;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/13 下午11:03
 * 描述:
 */
public class BGAPumpkinRefreshViewHolder extends BGARefreshViewHolder {
    private BGAPumpkinRefreshView mPumpkinRefreshView;
    private BGAPumpkinRefreshView mPumpkinFootRefreshView;
    private int mPullDownImageResId = -1;
    private int mChangeToReleaseRefreshAnimResId = -1;
    private int mRefreshingAnimResId = -1;

    private TextView mHeaderStatusTv;
    private TextView mFootStatusTv;
    private boolean isLoadingMoreEnabled = true;

    /**
     * @param context
     * @param isLoadingMoreEnabled 上拉加载更多是否可用
     */
    public BGAPumpkinRefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
        this.isLoadingMoreEnabled = isLoadingMoreEnabled;
    }

    /**
     * 设置下拉过程中的图片资源
     *
     * @param resId
     */
    public void setPullDownImageResource(@DrawableRes int resId) {
        mPullDownImageResId = resId;
    }

    /**
     * 设置进入释放刷新状态时的动画资源
     *
     * @param resId
     */
    public void setChangeToReleaseRefreshAnimResId(@DrawableRes int resId) {
        mChangeToReleaseRefreshAnimResId = resId;
    }

    /**
     * 设置正在刷新时的动画资源
     *
     * @param resId
     */
    public void setRefreshingAnimResId(@DrawableRes int resId) {
        mRefreshingAnimResId = resId;
    }

    @Override
    public View getRefreshHeaderView() {
        if (mRefreshHeaderView == null) {
            mRefreshHeaderView = inflate(mContext, R.layout.view_refresh_header_pumpkin, null);
            mRefreshHeaderView.setBackgroundColor(Color.TRANSPARENT);
            if (mRefreshViewBackgroundColorRes != -1) {
                mRefreshHeaderView.setBackgroundResource(mRefreshViewBackgroundColorRes);
            }
            if (mRefreshViewBackgroundDrawableRes != -1) {
                mRefreshHeaderView.setBackgroundResource(mRefreshViewBackgroundDrawableRes);
            }

            mPumpkinRefreshView = (BGAPumpkinRefreshView) mRefreshHeaderView.findViewById(R.id.pumpkinView);
            mHeaderStatusTv = (TextView) mRefreshHeaderView.findViewById(R.id.tv_text);
            if (mPullDownImageResId != -1) {
                mPumpkinRefreshView.setPullDownImageResource(mPullDownImageResId);
            } else {
                throw new RuntimeException("请调用" + BGAPumpkinRefreshViewHolder.class.getSimpleName() + "的setPullDownImageResource方法设置下拉过程中的图片资源");
            }
            if (mChangeToReleaseRefreshAnimResId != -1) {
                mPumpkinRefreshView.setChangeToReleaseRefreshAnimResId(mChangeToReleaseRefreshAnimResId);
            } else {
                throw new RuntimeException("请调用" + BGAPumpkinRefreshViewHolder.class.getSimpleName() + "的setChangeToReleaseRefreshAnimResId方法设置进入释放刷新状态时的动画资源");
            }
            if (mRefreshingAnimResId != -1) {
                mPumpkinRefreshView.setRefreshingAnimResId(mRefreshingAnimResId);
            } else {
                throw new RuntimeException("请调用" + BGAPumpkinRefreshViewHolder.class.getSimpleName() + "的setRefreshingAnimResId方法设置正在刷新时的动画资源");
            }
            mHeaderStatusTv.setText(mPullDownRefreshText);
        }
        return mRefreshHeaderView;
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {
        if (scale <= 1.0f) {
            mPumpkinRefreshView.handleScale(scale);
        }
    }

    @Override
    public void changeToIdle() {
        mPumpkinRefreshView.changeToIdle();
    }

    @Override
    public void changeToPullDown() {
        mHeaderStatusTv.setText(mPullDownRefreshText);
        mPumpkinRefreshView.changeToPullDown();
    }

    @Override
    public void changeToReleaseRefresh() {
        mHeaderStatusTv.setText(mReleaseRefreshText);
        mPumpkinRefreshView.changeToReleaseRefresh();
    }

    @Override
    public void changeToRefreshing() {
        mHeaderStatusTv.setText(mRefreshingText);
        mPumpkinRefreshView.changeToRefreshing();
    }

    @Override
    public void onEndRefreshing() {
        mHeaderStatusTv.setText(mPullDownRefreshText);
        mPumpkinRefreshView.onEndRefreshing();
    }

    @Override
    public View getLoadMoreFooterView() {
        if (!isLoadingMoreEnabled) {
            return null;
        }
        if (mLoadMoreFooterView == null) {
            mLoadMoreFooterView = inflate(mContext, R.layout.view_refresh_header_pumpkin, null);
            mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
            if (mRefreshViewBackgroundColorRes != -1) {
                mLoadMoreFooterView.setBackgroundResource(mRefreshViewBackgroundColorRes);
            }
            if (mRefreshViewBackgroundDrawableRes != -1) {
                mLoadMoreFooterView.setBackgroundResource(mRefreshViewBackgroundDrawableRes);
            }
            mPumpkinFootRefreshView = (BGAPumpkinRefreshView) mLoadMoreFooterView.findViewById(R.id.pumpkinView);
            mFootStatusTv = (TextView) mLoadMoreFooterView.findViewById(R.id.tv_text);
            if (mRefreshingAnimResId != -1) {
                mPumpkinFootRefreshView.setRefreshingAnimResId(mRefreshingAnimResId);
            } else {
                throw new RuntimeException("请调用" + BGAPumpkinRefreshViewHolder.class.getSimpleName() + "的setRefreshingAnimResId方法设置正在刷新时的动画资源");
            }

        }
        return mLoadMoreFooterView;

    }

    @Override
    public void changeToLoadingMore() {
        mFootStatusTv.setText(mLodingMoreText);
        mPumpkinFootRefreshView.changeToFootRefreshing();
    }

    @Override
    public void onEndLoadingMore() {
        mPumpkinFootRefreshView.onEndRefreshing();
    }
}