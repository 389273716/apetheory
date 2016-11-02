package com.tc.apetheory.widgets;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

/**
 * 项目名称：shop_as
 * 类描述：自定义滑动view，解决惯性滑动以及本view内部嵌套recyclerview导致隐藏toolbar不生效
 * 创建人：TC
 * 创建时间：2015/9/22 15:04
 * 修改人：Administrator
 * 修改时间：2015/9/22 15:04
 * 修改备注：
 */
public class CustomNestedView extends NestedScrollView {
    private static final String TAG = CustomNestedView.class.getSimpleName();
    private int downX;
    private int downY;
    private int mTouchSlop;

    private OnNestedScrollListener onNestedScrollListener;

    /**
     * 监听滚动量
     * @param onNestedScrollListener
     */
    public void setOnNestedScrollListener(OnNestedScrollListener onNestedScrollListener) {
        this.onNestedScrollListener = onNestedScrollListener;
    }

    public interface OnNestedScrollListener {
        void onScroll(int scrollY);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onNestedScrollListener != null) {
            onNestedScrollListener.onScroll(t);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }



    public CustomNestedView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public CustomNestedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public CustomNestedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


}
