package com.example.jet.github.recyclerview;


import android.view.View;
import android.widget.TextView;

import com.example.jet.github.R;
import com.example.jet.github.model.User;
import com.example.jet.library.base.adapter.BaseAdapter;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by xingguangyao on 14/12/5.
 */
public class RecyclerAdapter extends BaseAdapter<User> {

    public RecyclerAdapter(List<User> mDatas, RecyclerViewItemClickListener mClickListener) {
        super(mDatas, mClickListener);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_main_recycler_item;
    }

    @Override
    public int getItemViewType(int position) {
        if ("group".equals(mDatas.get(position).type))
            return 0x003;
        else if ("child".equals(mDatas.get(position).type))
            return 0x004;
        return super.getItemViewType(position);
    }

    @Override
    protected BaseAdapter.ViewHolder getViewHolder(View itemView, RecyclerViewItemClickListener mClickListener, int viewType) {
        switch (viewType) {
            case 0x003:
                itemView = View.inflate(mContext, R.layout.fragment_main_recycler_group_item, null);
                return new GroupViewHolder(itemView, mClickListener);
            case 0x004:
                itemView = View.inflate(mContext, R.layout.fragment_main_recycler_child_item, null);
                return new ChildViewHolder(itemView, mClickListener);
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
            case TYPE_FOOTERVIEW:
                break;
            case 0x003:
                setGroupItemViewData(holder, position);
                break;
            case 0x004:
                setChildItemViewData(holder, position);
                break;
            default:
                setItemViewData(holder,  position );
                break;
        }

    }

    @Override
    protected User getData() {
        return new User();
    }


    private void setItemViewData(BaseAdapter.ViewHolder holder, int position) {
        ViewHolder mViewHolder = (ViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position).username);
    }
    private void setGroupItemViewData(BaseAdapter.ViewHolder holder, int position) {
        GroupViewHolder mViewHolder = (GroupViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position).username);
    }
    private void setChildItemViewData(BaseAdapter.ViewHolder holder, int position) {
        ChildViewHolder mViewHolder = (ChildViewHolder) holder;
        mViewHolder.tv.setText(mDatas.get(position).username);
    }

    public static class ViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public ViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }

    public static class GroupViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public GroupViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }
    public static class ChildViewHolder extends BaseAdapter.ViewHolder {

        @InjectView(R.id.tv)
        TextView tv;

        public ChildViewHolder(View itemView, BaseAdapter.RecyclerViewItemClickListener mClickListener) {
            super(itemView, mClickListener);
        }
    }


}
