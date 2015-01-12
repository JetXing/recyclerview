package com.example.jet.github.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.jet.github.R;
import com.example.jet.github.model.User;
import com.example.jet.library.base.viewholder.BaseViewHolder;

import butterknife.InjectView;

/**
 * Created by xingguangyao on 15/1/12.
 */
public class ViewHolder extends BaseViewHolder<User> {

    @InjectView(R.id.tv)
    TextView tv;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setViewData(Context mContext, User object, int position, int viewType) {
           tv.setText(object.username);
    }
}
