package com.example.jet.github.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.jet.github.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xingguangyao on 14/12/22.
 */
public class HeaderViewHelper {

    @InjectView(R.id.tv)
    TextView mTextView;

    private View mView;
    private Context mContext;

    public HeaderViewHelper(Context mContext){
        this.mContext = mContext;
    }

    public View getHeadView(){
        if (mView == null)
            mView = View.inflate(mContext, R.layout.fragment_main_recycler_header, null);
        ButterKnife.inject(this, mView);
        initView();
        return mView;
    }

    private void initView(){
        mTextView.setText("demo");
    }
}
