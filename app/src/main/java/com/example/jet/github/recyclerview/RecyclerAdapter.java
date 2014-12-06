package com.example.jet.github.recyclerview;


import android.view.View;
import android.widget.TextView;

import com.example.jet.github.R;
import com.example.jet.github.base.adapter.BaseAdapter;

import java.util.ArrayList;
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

//    @Override
//    protected int getHeaderViewResourceId() {
//        return R.layout.fragment_main_recycler_header;
//    }
//
//    @Override
//    protected int getFooterViewResourceId() {
//        return R.layout.fragment_main_recycler_footer;
//    }

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
                setHeaderViewData(holder, position);
                break;
            case TYPE_FOOTERVIEW:
                setFooterViewData(holder, position);

                break;
            default:
                setItemViewData(holder, position);
                break;
        }

    }

    private void setHeaderViewData(BaseAdapter.ViewHolder holder, int position) {
        HeaderViewHolder mViewHolder = (HeaderViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position));
    }

    private void setItemViewData(BaseAdapter.ViewHolder holder, int position) {
        ViewHolder mViewHolder = (ViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position));
    }

    private void setFooterViewData(BaseAdapter.ViewHolder holder, int position) {
        FooterViewHolder mViewHolder = (FooterViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position));
    }

    public static class ViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }

    public static class HeaderViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public HeaderViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }

    public static class FooterViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public FooterViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }
}
