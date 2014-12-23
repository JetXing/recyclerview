package com.example.jet.library.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xingguangyao on 14/12/5.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    protected static final int TYPE_HEADERVIEW = 0x001;
    protected static final int TYPE_FOOTERVIEW = 0x002;

    protected Context mContext;
    protected List<T> mDatas = new ArrayList<T>();
    protected RecyclerViewItemClickListener mClickListener;

    protected List<View> mHeaderViewInfos = new ArrayList<View>();
//    protected List<View> mFooterViewInfos = new ArrayList<View>();

    protected View headerView;

    public View getFooterView() {
        return footerView;
    }

    public void addFooterView(View footerView) {
//        mFooterViewInfos.add(footerView);
        this.footerView = footerView;
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
    }

    public void removeHeaderView() {
        this.headerView = null;
    }

    protected View footerView;

    public BaseAdapter(List<T> mDatas, RecyclerViewItemClickListener mClickListener) {
        super();
        this.mDatas = mDatas;
        this.mClickListener = mClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View mView;
        switch (viewType) {
            case TYPE_HEADERVIEW:
                mView = getHeaderView();
                break;
            case TYPE_FOOTERVIEW:
                mView = getFooterView();
                break;
            default:
                mView = LayoutInflater.from(mContext)
                        .inflate(getResourceId(), parent, false);
                break;
        }
        return getViewHolder(mView, mClickListener, viewType);
    }

    @Override
    public  int getItemViewType(int position) {

        int count = getItemCount() - 1;
        if (isHeaderView(position)) {
            return TYPE_HEADERVIEW;
        } else if (isFooterView(position, count)) {
            return TYPE_FOOTERVIEW;
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setViewData(holder, position, getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        int size = mDatas.size();
        return size;
    }

    protected abstract int getResourceId();

    protected abstract ViewHolder getViewHolder(View itemView, RecyclerViewItemClickListener mClickListener, int viewType);

    protected abstract void setViewData(ViewHolder holder, int position, int viewType);

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected RecyclerViewItemClickListener mClickListener;

        public ViewHolder(View itemView, RecyclerViewItemClickListener mClickListener) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.mClickListener = mClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onClick(v, getPosition());

            }
        }
    }

    /**
     * 代替listview的setOnItemClickListener
     */
    public interface RecyclerViewItemClickListener {
        void onClick(View itemView, int position);
    }

    public static class HeaderViewHolder extends BaseAdapter.ViewHolder {

        public HeaderViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, null);
        }
    }

    public static class FooterViewHolder extends BaseAdapter.ViewHolder {

        public FooterViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, null);
        }
    }

}
