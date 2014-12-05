package com.example.jet.github.base.adapter;

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
    protected boolean isHaveHeaderView = false;
    protected boolean isHaveFooterView = false;

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
                mView = LayoutInflater.from(mContext)
                        .inflate(getHeaderViewResourceId(), parent, false);
                break;
            case TYPE_FOOTERVIEW:
                mView = LayoutInflater.from(mContext)
                        .inflate(getFooterViewResourceId(), parent, false);
                break;
            default:
                mView = LayoutInflater.from(mContext)
                        .inflate(getResourceId(), parent, false);

        }
        return getViewHolder(mView, mClickListener, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        int count = getItemCount() - 1;
        if (position == 0 && isHaveHeaderView){
                return TYPE_HEADERVIEW;
        } else if (position == count && isHaveFooterView){
                return TYPE_FOOTERVIEW;
        } else {
            return super.getItemViewType(position);

        }
    }



    protected abstract int getHeaderViewResourceId();

    protected abstract int getFooterViewResourceId();

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setViewData(holder, position, getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
            mClickListener.onClick(v, getPosition());
        }
    }

    /**
     * 代替listview的setOnItemClickListener
     */
    public interface RecyclerViewItemClickListener {
        void onClick(View itemView, int position);
    }

    public boolean isHaveHeaderView() {
        return isHaveHeaderView;
    }

    public void setHaveHeaderView(boolean isHaveHeaderView) {
        this.isHaveHeaderView = isHaveHeaderView;
    }

    public boolean isHaveFooterView() {
        return isHaveFooterView;
    }

    public void setHaveFooterView(boolean isHaveFooterView) {
        this.isHaveFooterView = isHaveFooterView;
    }
}
