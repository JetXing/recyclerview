package com.example.jet.library.base.viewholder;

import android.content.Context;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by xingguangyao on 15/1/12.
 */
public interface CardInit<T> {

    Card getCard(Context mContext, T object, int position, int viewType);
}
