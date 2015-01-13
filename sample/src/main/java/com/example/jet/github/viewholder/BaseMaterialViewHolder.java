package com.example.jet.github.viewholder;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import com.example.jet.library.R;
import com.example.jet.library.base.viewholder.BaseViewHolder;
import com.example.jet.library.base.viewholder.CardInit;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.Constants;
import it.gmariotti.cardslib.library.view.CardViewNative;


/**
 * Created by xingguangyao on 15/1/12.
 */
public abstract class BaseMaterialViewHolder<T> extends BaseViewHolder<T> implements CardInit<T>{

    @InjectView(R.id.card_view_native)
    public CardViewNative mCardViewNative;

    public BaseMaterialViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setViewData(Context mContext, T object, int position, int viewType) {
        mCardViewNative.setCard(getCard(mContext, object, position, viewType));

        setAnimationToCardView();
    }

    protected void setAnimationToCardView() {
        mCardViewNative.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (Build.VERSION.SDK_INT >= Constants.API_L) {
                            mCardViewNative.animate().setDuration(100).scaleX(1.01f).scaleY(1.01f).translationZ(10);
                        } else {
                            mCardViewNative.animate().setDuration(100).scaleX(1.01f).scaleY(1.01f);
                        }
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        if (Build.VERSION.SDK_INT >= Constants.API_L) {
                            mCardViewNative.animate().setDuration(100).scaleX(1).scaleY(1).translationZ(0);
                        } else {
                            mCardViewNative.animate().setDuration(100).scaleX(1).scaleY(1);
                        }
                        return false;
                }
                return false;
            }
        });
    }

}
