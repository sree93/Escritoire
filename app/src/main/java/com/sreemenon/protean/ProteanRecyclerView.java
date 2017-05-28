package com.sreemenon.protean;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sreemenon.escritoire.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinath on 28/5/17.
 */

public class ProteanRecyclerView extends RecyclerView {
    private final int TYPE_PARAGRAPH = 0;

    private Context context;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<ProteanElement> elementList;

    public ProteanRecyclerView(Context context) {
        super(context);
        initialize(context);
    }

    public ProteanRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ProteanRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context){
        this.context = context;
        elementList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this.getContext());
        setLayoutManager(mLayoutManager);
    }

    public void setRichText(String richText){
        elementList = ProteanElement.GetProteanList(richText);
        setHasFixedSize(false);
        setAdapter(new ProteanAdapter(elementList));
    }

    class ProteanAdapter extends RecyclerView.Adapter{

        List<ProteanElement> elements;

        public ProteanAdapter(List<ProteanElement> elements) {
            this.elements = elements;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            switch (viewType){
                case TYPE_PARAGRAPH:
                    View v_paragraph = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.protean_p, parent, false);
                    return new ProteanParagraphViewHolder(v_paragraph);
            }

            return null;
        }

        @Override
        public int getItemViewType(int position) {
            switch (elements.get(position).getTag()){
                case Constants.TAG_PARAGRAPH:
                    return TYPE_PARAGRAPH;
            }
            return -1;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (holder.getItemViewType()){
                case TYPE_PARAGRAPH:
                    ((ProteanParagraphViewHolder)holder).tvParagraph.setText(elements.get(position).getText());
            }
        }

        @Override
        public int getItemCount() {
            return elements.size();
        }
    }
}
