package com.example.jet.library.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.jet.library.R;
import com.example.jet.library.base.viewholder.BaseViewHolder;
import com.example.jet.library.base.viewholder.FooterViewHolder;
import com.example.jet.library.base.viewholder.HeaderViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xingguangyao on 14/12/5.
 */
public abstract class BaseMaterialAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected List<T> mDatas = new ArrayList<T>();
    private RecyclerViewItemClickListener clickListener;
    private RecyclerViewItemLongClickListener longClickListener;
    private RecyclerViewItemTouchClickListener touchClickListener;

    protected int card_layout_resourceID = R.layout.card_view_layout;

    protected List<View> mHeaderViewInfos = new ArrayList<View>();

    protected View headerView;

    private boolean mNotifyOnChange = true;

    private Object mLock = new Object();

    public View getFooterView() {
        return footerView;
    }

    public void addFooterView(View footerView) {
//        mFooterViewInfos.add(footerView);
        this.footerView = footerView;
        mDatas.add(mDatas.size(), getData());
    }

    public void removeFooterView() {
        this.footerView = null;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void addHeaderView(View headerView) {
        mHeaderViewInfos.add(headerView);
        this.headerView = headerView;
        mDatas.add(0, getData());
    }

    public void removeHeaderView() {
        this.headerView = null;
    }

    protected View footerView;

    public BaseMaterialAdapter(List<T> mDatas) {
        super();
        this.mDatas = mDatas;
    }

    public void setClickListener(RecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(RecyclerViewItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setTouchClickListener(RecyclerViewItemTouchClickListener touchClickListener) {
        this.touchClickListener = touchClickListener;
    }

    public void setmNotifyOnChange(boolean mNotifyOnChange) {
        this.mNotifyOnChange = mNotifyOnChange;
    }

    public boolean hasItem(T object) {
        synchronized (mLock) {
            return mDatas.contains(object);
        }
    }

    public void add(T object) {
        int itemInsert;
        synchronized (mLock) {
            itemInsert = mDatas.size();
            mDatas.add(object);
        }

        if (mNotifyOnChange) {
            notifyItemInserted(itemInsert);
        }
    }

    public void addAll(Collection<? extends T> collection) {
        int itemInsert;
        synchronized (mLock) {
            itemInsert = mDatas.size();
            mDatas.addAll(collection);
        }
        if (mNotifyOnChange) {
            notifyItemInserted(itemInsert);
        }
    }

    public void remove(int position) {
        synchronized (mLock) {
            mDatas.remove(position);
        }

        if (mNotifyOnChange) {
            notifyItemRemoved(position);
        }
    }

    public void remove(T object) {
        int itemRemove;
        synchronized (mLock) {
            itemRemove = mDatas.indexOf(object);
        }

        if (itemRemove >= 0) {
            remove(itemRemove);
        }
    }

    public void clear() {
        int itemRemoved;
        synchronized (mLock) {
            itemRemoved = mDatas.size();
            mDatas.clear();
        }
        if (mNotifyOnChange) {
            for (int i = itemRemoved - 1; i >= 0; i--) {
                notifyItemRemoved(i);
            }
        }
    }

    public int size() {
        synchronized (mLock) {
            return mDatas.size();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        mContext = parent.getContext();
        View mView = getView(parent, viewType);
        final BaseViewHolder viewHolder = getViewHolder(mView, viewType);

        if (viewHolder != null) {
            if (viewHolder.isClickable() && clickListener != null) {
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(viewHolder, viewHolder.getPosition());
                    }
                });
            } else {
                mView.setOnClickListener(null);
            }

            if (viewHolder.isLongClickable() && longClickListener != null) {
                mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return onItemLongClick(viewHolder, viewHolder.getPosition());
                    }
                });
            } else {
                mView.setOnLongClickListener(null);
            }

            if (viewHolder.isTouchable() && touchClickListener != null) {
                mView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return onItemTouch(viewHolder, viewHolder.getPosition());
                    }
                });
            } else {
                mView.setOnTouchListener(null);
            }
        }

        return viewHolder;
    }

    private View getView(ViewGroup parent, int viewType) {
        View mView;
        switch (viewType) {
            case BaseViewType.TYPE_HEADERVIEW:
                mView = getHeaderView();
                break;
            case BaseViewType.TYPE_FOOTERVIEW:
                mView = getFooterView();
                break;
            default:
                if (getResourceId(viewType) != 0) {
                    card_layout_resourceID = getResourceId(viewType);
                } else {
                    card_layout_resourceID = R.layout.card_view_layout;
                }
                mView = LayoutInflater.from(mContext)
                        .inflate(card_layout_resourceID, parent, false);
                break;
        }
        return mView;
    }

    @Override
    public int getItemViewType(int position) {

        int count = getItemCount() - 1;
        if (isHeaderView(position)) {
            return BaseViewType.TYPE_HEADERVIEW;
        } else if (isFooterView(position, count)) {
            return BaseViewType.TYPE_FOOTERVIEW;
        } else {
            return super.getItemViewType(position);
        }
    }

    private boolean isFooterView(int position, int count) {
        return position == count && footerView != null;
    }

    private boolean isHeaderView(int position) {
        int size = mHeaderViewInfos.size();
        if (position < size && headerView != null) {
            headerView = mHeaderViewInfos.get(position);
            return true;
        }
        return false;
    }

    public T get(int position) {
        synchronized (mLock) {
            return mDatas.get(position);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T object;
        synchronized (mLock) {
            object = mDatas.get(position);
        }

        switch (getItemViewType(position)) {
            case BaseViewType.TYPE_HEADERVIEW:
            case BaseViewType.TYPE_FOOTERVIEW:
                break;
            default:
                holder.onBindView(object, position, getItemViewType(position));
//                setItemViewData(holder,  position );
                break;
        }

    }

    @Override
    public int getItemCount() {
        int size = mDatas.size();
        return size;
    }

    private void onItemClick(BaseViewHolder viewHolder, int position) {
        if (clickListener != null) {
            View view = viewHolder.getmView();
            clickListener.onItemClick(view, position);
        }
    }

    private boolean onItemLongClick(BaseViewHolder viewHolder, int position) {
        if (longClickListener != null) {
            View view = viewHolder.getmView();
            return longClickListener.onItemLongClick(view, position);
        }
        return false;
    }

    private boolean onItemTouch(BaseViewHolder viewHolder, int position) {
        if (touchClickListener != null) {
            View view = viewHolder.getmView();
            return touchClickListener.onItemTouchClick(view, position);
        }
        return false;
    }

    protected abstract int getResourceId(int viewType);

    protected BaseViewHolder getViewHolder(View itemView, int viewType){
        switch (viewType) {
            case BaseViewType.TYPE_HEADERVIEW:
                return new HeaderViewHolder(itemView);
            case BaseViewType.TYPE_FOOTERVIEW:
                return new FooterViewHolder(itemView);
            default:
                return getAdvancedViewHolder(itemView, viewType);
        }
    }

    protected abstract T getData();

    protected abstract BaseViewHolder getAdvancedViewHolder(View itemView, int viewType);

    /**
     * click
     */
    public interface RecyclerViewItemClickListener {
        void onItemClick(View itemView, int position);
    }

    /**
     * long click
     */
    public interface RecyclerViewItemLongClickListener {
        boolean onItemLongClick(View itemView, int position);
    }

    /**
     * touch
     */
    public interface RecyclerViewItemTouchClickListener {
        boolean onItemTouchClick(View itemView, int position);
    }

}
