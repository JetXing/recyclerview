package com.example.jet.library.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by xingguangyao on 14/12/5.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getView(inflater, container);
    }

    protected View getView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View mView = inflater.inflate(getlayoutResID(), container, false);
        ButterKnife.inject(this, mView);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
    }

    protected void init() {
        initUI();
        initData();
    }

    protected abstract int getlayoutResID();

    protected abstract void initUI();

    protected abstract void initData();

}
