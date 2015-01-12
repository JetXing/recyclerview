package com.example.jet.github.recyclerview;


import android.view.View;

import com.example.jet.github.R;
import com.example.jet.github.model.User;
import com.example.jet.github.viewholder.ChildViewHolder;
import com.example.jet.github.viewholder.GroupViewHolder;
import com.example.jet.github.viewholder.ViewHolder;
import com.example.jet.library.base.adapter.BaseMaterialAdapter;
import com.example.jet.library.base.adapter.BaseViewType;
import com.example.jet.library.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by xingguangyao on 14/12/5.
 */
public class RecyclerMaterialAdapter extends BaseMaterialAdapter<User> {

    public RecyclerMaterialAdapter(List<User> mDatas) {
        super(mDatas);
    }


    @Override
    public int getItemViewType(int position) {
        if ("group".equals(mDatas.get(position).type))
            return BaseViewType.TYPE_GROUPVIEW;
        else if ("child".equals(mDatas.get(position).type))
            return BaseViewType.TYPE_CHILDRVIEW;
        return super.getItemViewType(position);
    }

    @Override
    protected int getResourceId(int viewType) {
        switch (viewType) {
            case BaseViewType.TYPE_GROUPVIEW:
                return R.layout.fragment_main_recycler_group_item;
            case BaseViewType.TYPE_CHILDRVIEW:
                return 0;
            default:
                return R.layout.fragment_main_recycler_item;
        }
    }

    @Override
    protected User getData() {
        return new User();
    }

    @Override
    protected BaseViewHolder getAdvancedViewHolder(View itemView, int viewType) {
        switch (viewType) {
            case BaseViewType.TYPE_GROUPVIEW:
                return new GroupViewHolder(itemView);
            case BaseViewType.TYPE_CHILDRVIEW:
                return new ChildViewHolder(itemView);
            default:
                return new ViewHolder(itemView);
        }
    }


}
