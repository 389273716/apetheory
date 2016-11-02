package com.tc.apetheory.presenter;

import com.tc.apetheory.presenter.iview.IArticleDetail;
import com.tc.apetheory.view.activity.BaseActivity;

/**
 * author：   tc
 * date：     2016/11/2 & 20:54
 * version    1.0
 * description
 * modify by
 */

public class ArticleDetailPresenter extends BasePresenter<IArticleDetail> {
    public ArticleDetailPresenter(IArticleDetail iView, BaseActivity baseActivity) {
        super(iView, baseActivity);
    }
}
