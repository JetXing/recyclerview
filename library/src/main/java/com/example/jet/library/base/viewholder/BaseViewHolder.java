package com.example.jet.library.base.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by xingguangyao on 15/1/12.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private View mView;
    private T object;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        mView = itemView;
    }

    public void onBindView(T object, int position, int viewType){
        this.object = object;
        setViewData(mView.getContext(), object, position, viewType);
    }

    public abstract void setViewData(Context mContext, T object, int position, int viewType);

    public View getmView() {
        return mView;
    }

    public Context getContext(){
        return mView.getContext();
    }

    public boolean isClickable(){
        return true;
    }

    public boolean isLongClickable(){
        return true;
    }

    public boolean isTouchable(){
        return true;
    }

}
