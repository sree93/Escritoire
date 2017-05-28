package com.sreemenon.protean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sreemenon.escritoire.R;

/**
 * Created by srinath on 28/5/17.
 */

public class ProteanParagraphViewHolder extends RecyclerView.ViewHolder {

    TextView tvParagraph;

    public ProteanParagraphViewHolder(View itemView) {
        super(itemView);
        tvParagraph = (TextView) itemView.findViewById(R.id.protean__tv_p);
    }

    public TextView getTvParagraph() {
        return tvParagraph;
    }
}
