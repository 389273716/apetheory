package com.tc.apetheory.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.tc.apetheory.common.BundleKeyContants;
import com.tc.apetheory.presenter.ArticleDetailPresenter;
import com.tc.apetheory.presenter.iview.IArticleDetail;
import com.tc.apetheory.widgets.CustomViewContainWebView;
import com.tc.apetheory.widgets.CustomWebView;
import com.tomtop.ttutil.DensityUtil;
import com.tomtop.ttutil.log.LogUtil;

/**
 * author：   tc
 * date：     2016/5/20 & 19:56
 * version    1.0
 * description
 * modify by
 */
public class ArticleDetailFragment extends BaseViewFragment<ArticleDetailPresenter> implements
        IArticleDetail {
    private static final String TAG = ArticleDetailFragment.class.getSimpleName();
    private ArticleDetailPresenter mArticleDetailPresenter;
    private String mUrl;
    private ProgressBar mProgressbar;


    private CustomWebView mWebView;

    private CustomViewContainWebView mCustomViewContainWebView;
    public ArticleDetailFragment() {
    }

    @Override
    public void obtainData() {
        mUrl = "";
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyContants.URL);
            LogUtil.e(TAG, "" + mUrl);
        }
        mWebView.loadUrl(mUrl);
    }

    public static ArticleDetailFragment newInstance(String url) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyContants.URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mWebView.stopLoading();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mCustomViewContainWebView.removeView(mWebView);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);// 把destroy()延后
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected View setContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle
            bundle) {
        mCustomViewContainWebView = new CustomViewContainWebView(mBaseViewActivity);
        return mCustomViewContainWebView;
    }


    @Override
    public ArticleDetailPresenter getPresenter() {
        return mArticleDetailPresenter;
    }



    @Override
    public void initView() {
        mArticleDetailPresenter = new ArticleDetailPresenter(this, mBaseViewActivity);
        mWebView = mCustomViewContainWebView.getWebView();

        mProgressbar = new ProgressBar(mBaseViewActivity, null,
                android.R.attr.progressBarStyleHorizontal);
        mProgressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams
                .FILL_PARENT,
                DensityUtil.dip2px(mBaseViewActivity, 5), 0, 0));
        mWebView.addView(mProgressbar);
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.setWebChromeClient(new CustomWebChromeClient());
        swithToSelectAndCopyTextMode();
    }

    @Override
    public void bindData() {

    }



    public void swithToSelectAndCopyTextMode() {
        try {
            KeyEvent shiftPressEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
            shiftPressEvent.dispatch(mWebView);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onBackPressedSupport();
    }


    private class CustomWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.i(TAG, "onProgressChanged: ");
            if (newProgress == 100) {
                // 加载成功，通知LoadLayout隐藏

                mProgressbar.setVisibility(View.GONE);
            } else {
                if (mProgressbar.getVisibility() == View.GONE)
                    mProgressbar.setVisibility(View.VISIBLE);
                mProgressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class CustomWebViewClient extends WebViewClient {

        /**
         * 加载失败，通知LoadLayout隐藏
         */
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {

            LogUtil.i(TAG, "onReceivedError");
        }

        // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            // 如果不需要其他对点击链接事件的处理返回true，否则返回false
            return false;

        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!mWebView.getSettings().getLoadsImagesAutomatically()) {
                mWebView.getSettings().setLoadsImagesAutomatically(true);
            }
        }
    }

}
