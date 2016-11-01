//package com.tc.apetheory.widgets;
//
//import android.animation.Animator;
//import android.animation.ObjectAnimator;
//import android.animation.PropertyValuesHolder;
//import android.content.Context;
//import android.os.Build;
//import android.os.Handler;
//import android.os.Message;
//import android.support.design.widget.FloatingActionButton;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.webkit.CookieManager;
//import android.webkit.CookieSyncManager;
//import android.webkit.WebSettings;
//import android.widget.RelativeLayout;
//
//import com.tomtop.shop.R;
//import com.tomtop.ttutil.log.LogUtil;
//
//import java.lang.ref.WeakReference;
//
//import static com.umeng.message.proguard.ay.R;
//

//public class CustomViewContainWebView extends RelativeLayout {
//
//    private static final String TAG = CustomViewContainWebView.class.getSimpleName();
//
//    private ObjectAnimator mObjectAnimator;
//
//    protected FloatingActionButton mFabBacktop;
//
//    private CustomWebView mWebView;
//    private BackHandler mBackHandler;
//
//
//    public void destroy() {
//        if (mBackHandler != null) {
//            mBackHandler.removeCallbacksAndMessages(null);//销毁handler
//        }
//    }
//
//    private static class BackHandler extends Handler {
//        private WeakReference<CustomViewContainWebView> webview;
//
//        public BackHandler(CustomViewContainWebView webview) {
//            this.webview = new WeakReference<>(webview);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (webview.get() != null) {
//                webview.get().animOut();
//            }
//        }
//    }
//
//    private void animOut() {
//        animateOut(mFabBacktop);
//    }
//
//    public CustomViewContainWebView(Context context) {
//        super(context);
//        init(context);
//    }
//
//    public CustomViewContainWebView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
//
//
//    public CustomViewContainWebView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context);
//    }
//
//    private void init(Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_web_view, this, true);
//        findView(view);
//        initView();
//        setEvent();
//    }
//
//    private void initView() {
//        mWebView = new CustomWebView(getContext());
//        LayoutParams lp = new LayoutParams(LayoutParams
//                .MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        mWebView.setLayoutParams(lp);
//        this.addView(mWebView);
//        CookieSyncManager.createInstance(getContext());
//        CookieManager.getInstance().removeAllCookie();
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setLoadWithOverviewMode(true);
//        if (Build.VERSION.SDK_INT >= 19) {
//            webSettings.setLoadsImagesAutomatically(true);
//        } else {
//            webSettings.setLoadsImagesAutomatically(false);
//        }
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setAllowFileAccess(true);
//        webSettings.setPluginState(WebSettings.PluginState.ON);
//        webSettings.setDisplayZoomControls(false);
//
//        mWebView.getSettings().setSupportZoom(true); // 可以缩放
//        webSettings.setJavaScriptEnabled(true);
//
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//
//        mBackHandler = new BackHandler(this);
//    }
//
//    public CustomWebView getWebView() {
//        return mWebView;
//    }
//
//    private void findView(View view) {
//        mFabBacktop = (FloatingActionButton) view.findViewById(R.id.fab_backtop);
//
//    }
//
//    private void animateOut(final FloatingActionButton button) {
//        if (null != mObjectAnimator) {
//            mObjectAnimator.cancel();
//        }
//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0);
//        mObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(button, alpha);
//        mObjectAnimator.setDuration(500);
//        mObjectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mObjectAnimator = null;
//                button.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        mObjectAnimator.start();
//    }
//
//
//    private void animateIn(FloatingActionButton button) {
//
//        if (View.VISIBLE == button.getVisibility()) {
//            return;
//        }
//        button.setVisibility(View.VISIBLE);
//        if (null != mObjectAnimator) {
//            mObjectAnimator.cancel();
//        }
//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
//        mObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(button, alpha);
//        mObjectAnimator.setDuration(500);
//        mObjectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mObjectAnimator = null;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        mObjectAnimator.start();
//    }
//
//    private void setEvent() {
//        mFabBacktop.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mWebView.scrollTo(mWebView.getScrollX(), 0);
//            }
//        });
//
//        mWebView.setOnCustomScroolChangeListener(new CustomWebView.ScrollInterface() {
//
//            @Override
//            public void onSChanged(int l, int t, int oldl, int oldt) {
//                try {
//                    if (mWebView.getScrollY() == 0) { // 在最顶部
//                        animateOut(mFabBacktop);
//                    } else {
//                        animateIn(mFabBacktop);
//                        mBackHandler.removeMessages(1);
//                        mBackHandler.sendEmptyMessageDelayed(1, 3000);
//                    }
//                } catch (Exception e) {
//                    LogUtil.d(TAG, e.toString(), e);
//                }
//            }
//        });
//
//
//    }
//}
