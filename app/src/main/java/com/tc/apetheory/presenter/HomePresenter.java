package com.tc.apetheory.presenter;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tc.apetheory.entity.response.HomeJson;
import com.tc.apetheory.model.HomeModel;
import com.tc.apetheory.presenter.base.IHomeFragment;
import com.tc.apetheory.view.activity.BaseActivity;
import com.tomtop.ttcom.http.volley.ResponseCallback;
import com.tomtop.ttutil.CollectionUtil;
import com.tomtop.ttutil.log.LogUtil;

/**
 * @author tc
 * @version 1.0
 * @date 2016/3/27 16:34
 * @description 主页逻辑层
 */
public class HomePresenter extends BasePresenter<IHomeFragment> implements
        ResponseCallback<HomeJson> {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private HomeModel mHomeModel;
    private boolean mIsLoadMore;

    public HomePresenter(IHomeFragment iHomeFragment, BaseActivity baseActivity) {
        super(iHomeFragment, baseActivity);
        mHomeModel = new HomeModel();
    }


    public void obtainData() {
        mIsLoadMore = false;
        mHomeModel.getHomeData(mIView.getReqEntity(), this, mIView.getReqTag());
    }

    public void loadMore() {
        mIsLoadMore = true;
        mHomeModel.getHomeData(mIView.getReqEntity(), this, mIView.getReqTag());
    }

    @Override
    public void onSuccess(HomeJson data, String msg) {
        LogUtil.json(TAG, "msg:" + msg + " " + new Gson().toJson(data));
        if (!mIsLoadMore) {
            if (data == null || CollectionUtil.isEmpty(data.getData())) {
                mIView.onEmpty();
            }
            mIView.onSuccess(data, msg);
        } else {
            if (data == null || CollectionUtil.isEmpty(data.getData())) {
                mIView.onloadMoreFailure(msg);
            }
            mIView.loadMoreSuccees(data, msg);
        }
    }

    @Override
    public void onFailed(int code, String msg, VolleyError error) {
        if (!mIsLoadMore) {
            mIView.onFailure(msg);
        } else {
            mIView.onloadMoreFailure(msg);
        }
    }
}
