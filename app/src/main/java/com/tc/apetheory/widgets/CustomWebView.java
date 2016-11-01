package com.tc.apetheory.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.tomtop.ttutil.log.LogUtil;


public class CustomWebView extends WebView{

    private static final String TAG = CustomWebView.class.getSimpleName();
    private ScrollInterface mScrollInterface;
    private long mLastKeyDownTime;


    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setOnCustomScroolChangeListener(ScrollInterface scrollInterface) {
        this.mScrollInterface = scrollInterface;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != mScrollInterface) {
            mScrollInterface.onSChanged(l, t, oldl, oldt);
        }
    }

    public interface ScrollInterface {
        void onSChanged(int l, int t, int oldl, int oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (MotionEvent.ACTION_UP == action
                && (action & MotionEvent.ACTION_MASK) != MotionEvent.ACTION_POINTER_UP) {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.d(TAG, "currentTimeMillis = " + currentTimeMillis);
            LogUtil.d(TAG, "mLastKeyDownTime  = " + mLastKeyDownTime);
            if (currentTimeMillis - mLastKeyDownTime < 500) {
                int count = 0;
                if (canZoomOut() && !canZoomIn()) { // 当前大小 <= 初始化大小 ,做放大动作
                    while (zoomOut() && count++ < 10) {
                        LogUtil.d(TAG, "缩小");
                    }
                } else { // 当前大小 > 初始化的大小，做缩小动作
                    while (zoomIn() && count++ < 10) {
                        LogUtil.d(TAG, "放大");
                    }
                }
                mLastKeyDownTime= 0;
            } else {
                mLastKeyDownTime = currentTimeMillis;
            }
        } else if (MotionEvent.ACTION_MOVE == action) {
            mLastKeyDownTime= 0;
        }
        LogUtil.d(TAG, "MotionEvent = " + event);
        return super.onTouchEvent(event);
    }



}

