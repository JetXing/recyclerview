package com.example.jet.github.viewholder;

import android.content.Context;
import android.view.View;

import com.example.jet.github.R;
import com.example.jet.github.model.User;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by xingguangyao on 15/1/12.
 */
public class ChildViewHolder extends BaseMaterialViewHolder<User> {

    public ChildViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public Card getCard(Context mContext, User object, int position, int viewType) {
        Card mCard = new Card(mContext);
        CardHeader mHeader = new CardHeader(mContext);
        mHeader.setTitle(object.username);
        mCard.addCardHeader(mHeader);

        CardThumbnail mCardThumbnail = new CardThumbnail(mContext);
        mCardThumbnail.setDrawableResource(R.drawable.ic_launcher);
        mCard.addCardThumbnail(mCardThumbnail);

        CardExpand mCardExpand = new CardExpand(mContext);
        mCardExpand.setTitle("expand");
        mCard.addCardExpand(mCardExpand);
//        setAnimationToCardView();
        return mCard;
    }
}
