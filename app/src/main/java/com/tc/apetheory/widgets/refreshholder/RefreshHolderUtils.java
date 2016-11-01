package com.tc.apetheory.widgets.refreshholder;


import android.content.Context;
import android.support.annotation.NonNull;

import com.tc.apetheory.R;

/**
 * author:qdd
 * date：     2016/10/14  & 16:02
 * version    1.0
 * description
 * modify by
 * 初始化xml时如果需要加载更多功能
 * <cn.bingoogolapple.refreshlayout.BGARefreshLayout>
 * 嵌套scrollView、recycleView或者其他控件
 * .BGARefreshLayout的直接子控件的高度请使用
 * android:layout_height="0dp"和android:layout_weight="1"
 * </cn.bingoogolapple.refreshlayout.BGARefreshLayout>
 */

public class RefreshHolderUtils {
    /**
     * 初始化控件， 这里根据主题日期做了两种下拉动画 以后有需求可以在这里更改
     *
     * @param refreshLayout
     * @param context
     * @param isLoadingMoreEnabled 是否设置加载更多 true为加载更多
     */
    public static void initRefreshHolder(@NonNull BGARefreshLayout refreshLayout, @NonNull
            Context context, @NonNull boolean isLoadingMoreEnabled) {
        if (refreshLayout == null)
            return;
        BGARefreshViewHolder refreshViewHolder;

            refreshViewHolder = new BGADefaultRefreshViewHolder(context, isLoadingMoreEnabled);

        refreshViewHolder.setLoadingMoreText(context.getString(R.string.loading_more));
        refreshViewHolder.setPullDownRefreshText(context.getString(R.string.pull_down_refresh));
        refreshViewHolder.setReleaseRefreshText(context.getString(R.string.release_refresh));
        refreshViewHolder.setRefreshingText(context.getString(R.string.refreshing));
        refreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    /**
     * 禁用下拉刷新
     *
     * @param refreshLayout
     */
    public static void forbiddenPullDown(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.setPullDownRefreshEnable(false);
    }

    /**
     * 判断下拉动画是否正在运行
     *
     * @param refreshLayout
     * @return true 正在运行
     */
    public static boolean isPullDownRefresh(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return false;
        return refreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING;
    }

    /**
     * 判断加载更多动画是否正在运行
     *
     * @param refreshLayout
     * @return true 正在运行
     */
    public static boolean isLoadingMore(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return false;
        return refreshLayout.isLoadingMore();
    }

    /**
     * 结束下拉动画
     *
     * @param refreshLayout
     */
    public static void endPullDownRefresh(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.endRefreshing();
    }

    /**
     * 结束加载更多动画
     *
     * @param refreshLayout
     */
    public static void endLoadingMoreRefresh(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.endLoadingMore();
    }

    /**
     * 通过代码方式控制进入正在刷新状态。应用场景：
     * 某些应用在activity的onStart方法中调用，自动进入正在刷新状态获取最新数据
     *
     * @param refreshLayout
     */
    public static void beginRefreshing(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.beginRefreshing();
    }

    /**
     * 通过代码方式控制进入加载更多状态
     *
     * @param refreshLayout
     */
    public static void beginLoadingMore(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.beginLoadingMore();
    }

    /**
     * 设置代理监听
     *
     * @param delegate
     * @param refreshLayout
     */
    public static void setDelegate(@NonNull BGARefreshLayout.BGARefreshLayoutDelegate delegate,
                                   @NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.setDelegate(delegate);
    }

    public static void endRefreshAll(@NonNull BGARefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        if (isLoadingMore(refreshLayout)) {
            endLoadingMoreRefresh(refreshLayout);
        }

        if (isPullDownRefresh(refreshLayout)) {
            endPullDownRefresh(refreshLayout);
        }
    }

    /**
     *
     */
//    public interface BGARefreshLayoutDelegate {
    /**
     * 开始刷新
     */
//        void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout);

    /**
     * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean
     * 来处理是否加载更多。否则使用endLoadingMore方法会失效
     *
     * @param refreshLayout
     * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
     */
//     boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout);
//    }
}
