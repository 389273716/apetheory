package com.tc.apetheory.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tomtop.ttutil.CollectionUtil;
import com.tomtop.ttutil.log.LogUtil;

import java.util.List;

/**
 * author：   tc
 * date：     2016/10/23 & 20:23
 * version    1.0
 * description 使用于有头部和尾部的布局，内部提供增加、删除、更新、替换数据的方法。
 * modify by
 */
public class BaseDataBindingAdapter<T> extends RecyclerView.Adapter<BaseDataBindingAdapter
        .DataBindingViewHolder> {
    private static final String TAG = BaseDataBindingAdapter.class.getSimpleName();
    private Context mContext;
    private int mLayoutId;
    private int mVarId;
    private List<T> mDataList;
    public static final int TYPE_HEADER = 1, TYPE_FOOTER = 2, TYPE_NORMAL = 0;
    private boolean haveHeader = false;
    private boolean haveFooter = false;
    private View headerView, footerView;
    private ItemClickListener itemClickListener;
    private OnBindingListener mOnBindingListener;
    protected Presenter mPresenter;
    protected Decorator mDecorator;

    public interface Presenter {

    }
    public interface Decorator {
        void decorator(DataBindingViewHolder holder, int position, int viewType);
    }

    public void setDecorator(Decorator decorator) {
        mDecorator = decorator;
    }

    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    protected Presenter getPresenter() {
        return mPresenter;
    }
    /**
     * adapter初始化构造，
     *
     * @param context  上下文
     * @param layoutId 布局id
     * @param varId    参数id
     * @param data     数据源
     */
    public BaseDataBindingAdapter(Context context, int layoutId, int varId, List<T> data) {
        if (context == null) {
            throw new NullPointerException("context is not allow null!");
        } else {
            mContext = context;
            mLayoutId = layoutId;
            mVarId = varId;
            mDataList = data;
        }
    }

    public void setOnItemCkickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnBindingListener(OnBindingListener listener) {
        mOnBindingListener = listener;
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new DataBindingViewHolder(headerView, viewType);
            case TYPE_FOOTER:
                return new DataBindingViewHolder(footerView, viewType);
            case TYPE_NORMAL:
                return new DataBindingViewHolder(View.inflate(mContext, mLayoutId, null), viewType);
            default:
                return new DataBindingViewHolder(View.inflate(mContext, mLayoutId, null), viewType);
        }

    }

    @Override
    public void onBindViewHolder(final DataBindingViewHolder holder, int position) {
        final int realPosition = holder.getLayoutPosition();
        switch (holder.viewType) {
            case TYPE_HEADER:
                break;
            case TYPE_FOOTER:
                break;
            case TYPE_NORMAL:
            default:
                ViewDataBinding binding = DataBindingUtil.bind(holder.itemView);
                Object data;
                if (haveHeader) {
                    data = mDataList.get(realPosition - 1);
                } else {
                    data = mDataList.get(realPosition);
                }

                if (itemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemClickListener.itemClick(holder.itemView, realPosition);
                        }
                    });
                }
//                binding.setVariable(BR.presenter, getPresenter());
                binding.setVariable(mVarId, data);
                binding.executePendingBindings();
                if (mOnBindingListener != null) {
                    mOnBindingListener.onHolderBinding(holder, realPosition);
                }
                break;
        }
        if (mDecorator != null) {
            mDecorator.decorator(holder, position, getItemViewType(position));
        }

    }

    /**
     * 调用之后请调用NotifyDataSetChange 如果在setAdapter之后
     *
     * @param view
     */
    public void addFooterView(View view) {
        haveFooter = true;
        footerView = view;
    }

    /**
     * 调用之后请调用NotifyDataSetChange 如果在setAdapter之后
     *
     * @param view
     */
    public void addHeaderView(View view) {
        haveHeader = true;
        headerView = view;
    }

    public void removeFooterView() {
        footerView = null;
        haveFooter = false;
    }

    public View getFooterView() {
        return footerView;
    }

    public void cleanData() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (CollectionUtil.isEmpty(mDataList)) {
            return;
        }

        notifyItemRangeRemoved(0, mDataList.size());
        mDataList.clear();
        notifyItemRangeChanged(0, mDataList.size());
    }

    public void insertItems(@NonNull List<T> list, @IntRange(from = 0) int startIndex) {

        if (mDataList == null) {
            return;
        }

        if (list == null || list.isEmpty()) {
            LogUtil.e(TAG, "插入的数据集为空或长度小于等于零, 请检查你的数据集!");
            return;
        }

        if (this.mDataList.containsAll(list)) {
            return;
        }

        notifyItemRangeInserted(startIndex, list.size());
        mDataList.addAll(startIndex, list);
        notifyItemRangeChanged(startIndex, mDataList.size());
    }

    public void insertItems(@NonNull List<T> list) {
        this.insertItems(list, mDataList.size());
    }

    public void insertItem(@NonNull T t, @IntRange(from = 0) int position) {

        if (mDataList == null) {
            return;
        }

        if (t == null) {
            LogUtil.e(TAG, "插入的数据为空, 请检查你的数据!");
            return;
        }

        if (this.mDataList.contains(t)) {
            return;
        }

        notifyItemInserted(position);
        mDataList.add(position, t);
        notifyItemRangeChanged(position, mDataList.size());
    }

    public void insertItem(@NonNull T t) {
        this.insertItem(t, mDataList.size());
    }

    public void replaceData(@NonNull List<T> list) {
        if (mDataList == null) {
            return;
        }

        if (list == null || list.isEmpty()) {
            LogUtil.e(TAG, "插入的数据集为空或长度小于等于零, 请检查你的数据集!");
            return;
        }

        if (mDataList.containsAll(list)) {
            return;
        }

        removeAll();

        insertItems(list);
    }

    public void updateItems(@IntRange(from = 0) int positionStart, @IntRange(from = 0) int
            itemCount) {
        notifyItemRangeChanged(positionStart, itemCount);
    }

    public void updateAll() {
        updateItems(0, mDataList.size());
    }

    public void removeItem(@IntRange(from = 0) int position) {
        if (CollectionUtil.isEmpty(mDataList) || position <= -1) {
            return;
        }
        notifyItemRemoved(position);
        mDataList.remove(position);
        notifyItemRangeChanged(position, mDataList.size());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (haveHeader) {
            count++;
        } else if (haveFooter) {
            count++;
        }
        return mDataList != null ? mDataList.size() + count : count;
    }

    public Context getContext() {
        return mContext;
    }

    public T getItem(@IntRange(from = 0) int position) {
        if (position <= -1) {
            return null;
        }
        return !CollectionUtil.isEmpty(mDataList) ? mDataList.get(position) : null;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && haveHeader) {
            return TYPE_HEADER;
        } else if (position == mDataList.size() && haveFooter) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    public static class DataBindingViewHolder extends RecyclerView.ViewHolder {

        int viewType;

        public DataBindingViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
        }
    }

    public interface ItemClickListener {
        void itemClick(View view, int position);
    }

    public interface OnBindingListener {
        void onHolderBinding(DataBindingViewHolder holder, int position);
    }
}