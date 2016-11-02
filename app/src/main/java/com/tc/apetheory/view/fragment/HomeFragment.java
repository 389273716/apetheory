package com.tc.apetheory.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tc.apetheory.R;
import com.tc.apetheory.common.ShareConstants;
import com.tc.apetheory.entity.entities.HomeArticle;
import com.tc.apetheory.entity.entities.PageReqEntity;
import com.tc.apetheory.entity.response.HomeJson;
import com.tc.apetheory.presenter.HomePresenter;
import com.tc.apetheory.presenter.base.IHomeFragment;
import com.tc.apetheory.view.adapter.HomeArticleAdapter;
import com.tc.apetheory.widgets.ListSpacingDecoration;
import com.tc.apetheory.widgets.refreshholder.BGARefreshLayout;
import com.tc.apetheory.widgets.refreshholder.RefreshHolderUtils;
import com.tomtop.ttcom.view.adapter.RecyclerBaseAdapter;
import com.tomtop.ttutil.PreferencesUtil;
import com.tomtop.ttutil.log.LogUtil;

import java.util.List;

/**
 * author：   tc
 * date：     2016/5/20 & 16:51
 * version    1.0
 * description
 * modify by
 */
public class HomeFragment extends BaseViewFragment<HomePresenter> implements IHomeFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView mRecycleView;
    private HomePresenter mHomePresenter;
    private int mPage = 1;
    private PageReqEntity mPageReqEntity;
    private BGARefreshLayout mRefreshLayout;
    private HomeArticleAdapter mHomeArticleAdapter;
    private boolean mIsRefresh;

    public HomeFragment() {
    }

    @Override
    public void obtainData() {
        mHomePresenter = new HomePresenter(this, mBaseViewActivity);

        mPageReqEntity = new PageReqEntity();
        mPage = 1;
        //获取缓存
        String homeDataStr = PreferencesUtil.getString(getActivity(), ShareConstants
                        .NAME_HOME_CACHE_DATA,
                ShareConstants.KEY_HOME_CACHE_DATA, "");
        try {
            HomeJson homeJson = new Gson().fromJson(homeDataStr, HomeJson.class);
            initData(homeJson.getData());
            return;
        } catch (Exception e) {
            LogUtil.e(e.toString());
        }

        mHomePresenter.obtainData();
    }

    @Override
    public void initView() {
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.refresh_home_list);
        mRecycleView = (RecyclerView) findViewById(R.id
                .rv_home_list);
        mRecycleView.setHasFixedSize(false);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mBaseViewActivity));
        mRecycleView.addItemDecoration(new ListSpacingDecoration(mBaseViewActivity, R.dimen
                .item_vertical_margin));

        RefreshHolderUtils.initRefreshHolder(mRefreshLayout, getActivity(), true);
        RefreshHolderUtils.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                mPage = 1;
                mHomePresenter.obtainData();
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                mPage++;
                mPageReqEntity.setPage(mPage);
                mHomePresenter.loadMore();
                return false;
            }
        }, mRefreshLayout);
    }

    private void initData(List<HomeArticle> list) {
        mHomeArticleAdapter = new HomeArticleAdapter(mBaseViewActivity, list);
        mRecycleView.setAdapter(mHomeArticleAdapter);
        mHomeArticleAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View view, int position) {
                LogUtil.e(position + " pos");
                if (mHomeArticleAdapter != null) {
                    HomeArticle item = mHomeArticleAdapter.getItem(position);
                    if (item != null) {
                        start(ArticleDetailFragment.newInstance(item.getUrl()));
                    }
                }
            }
        });

    }

    @Override
    public void bindData() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    protected View setContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle
            bundle) {
        return layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);

    }


    @Override
    public PageReqEntity getReqEntity() {
        mPageReqEntity.setPage(mPage);
        return mPageReqEntity;
    }

    @Override
    public String getReqTag() {
        return TAG;
    }

    @Override
    public void onSuccess(HomeJson homeJson, String msg) {
        endRefreshView();
        LogUtil.e("load data onSuccess");
        PreferencesUtil.putString(getActivity(), ShareConstants
                .NAME_HOME_CACHE_DATA, ShareConstants.KEY_HOME_CACHE_DATA, new Gson().toJson
                (homeJson));
        initData(homeJson.getData());

    }

    @Override
    public void onFailure(String msg) {
        endRefreshView();
        LogUtil.e("load data onFailure");
    }

    private void endRefreshView() {
        if (mRefreshLayout != null) {
            RefreshHolderUtils.endPullDownRefresh(mRefreshLayout);
        }
    }

    @Override
    public void onEmpty() {
        LogUtil.e("load data onEmpty");
    }

    @Override
    public void loadMoreSuccees(HomeJson homeJson, String msg) {
        endLoadMoreView();
        LogUtil.e("loadmore success");
        int count = mHomeArticleAdapter.getItemCount();
        mHomeArticleAdapter.insertItems(homeJson.getData());
        mRecycleView.smoothScrollToPosition(count);
    }

    @Override
    public void onloadMoreFailure(String msg) {
        endLoadMoreView();
        mPage--;
        mPageReqEntity.setPage(mPage);
        LogUtil.e(" onloadMoreFailure");
    }

    private void endLoadMoreView() {
        if (mRefreshLayout != null) {
            RefreshHolderUtils.endLoadingMoreRefresh(mRefreshLayout);
        }
    }

    @Override
    public HomePresenter getPresenter() {
        return mHomePresenter;
    }
}
