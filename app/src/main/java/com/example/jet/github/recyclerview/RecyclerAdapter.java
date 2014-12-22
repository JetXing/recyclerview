package com.example.jet.github.recyclerview;


import android.view.View;
import android.widget.TextView;

import com.example.jet.github.R;
import com.example.jet.github.base.adapter.BaseAdapter;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by xingguangyao on 14/12/5.
 */
public class RecyclerAdapter extends BaseAdapter<String> {

    public RecyclerAdapter(List<String> mDatas, RecyclerViewItemClickListener mClickListener) {
        super(mDatas, mClickListener);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_main_recycler_item;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public void addHeaderView(View headerView) {
        super.addHeaderView(headerView);
        mDatas.add(0, "h");
    }

    @Override
    public void addFooterView(View footerView) {
        super.addFooterView(footerView);
        mDatas.add(mDatas.size(), "f");
    }

    @Override
    protected BaseAdapter.ViewHolder getViewHolder(View itemView, RecyclerViewItemClickListener mClickListener, int viewType) {
        switch (viewType) {
            case TYPE_HEADERVIEW:
                return new HeaderViewHolder(itemView, mClickListener);
            case TYPE_FOOTERVIEW:
                return new FooterViewHolder(itemView, mClickListener);
            default:
                return new ViewHolder(itemView, mClickListener);
        }

    }

    @Override
    protected void setViewData(BaseAdapter.ViewHolder holder, int position, int viewType) {
        switch (viewType) {
            case TYPE_HEADERVIEW:
                break;
            case TYPE_FOOTERVIEW:
                break;
            default:
                setItemViewData(holder,  position );
                break;
        }

    }


    private void setItemViewData(BaseAdapter.ViewHolder holder, int position) {
        ViewHolder mViewHolder = (ViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position));
    }

    public static class ViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }


}
