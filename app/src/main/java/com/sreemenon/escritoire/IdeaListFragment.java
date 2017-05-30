package com.sreemenon.escritoire;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.sreemenon.db.IdeaModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link IdeaListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdeaListFragment extends MyFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public IdeaListFragment() {
        super(R.layout.fragment_idea_list, R.drawable.ic_add_black_24dp);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static IdeaListFragment newInstance() {
        IdeaListFragment fragment = new IdeaListFragment();
        return fragment;
    }

    @Override
    void initUIElements(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.frag_idea_list_recycler_view);
        recyclerView.hasFixedSize();

        mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        List<IdeaModel> entries = IdeaModel.listAll(IdeaModel.class, "created_at desc");

        mAdapter = new IdeaEntryAdapter(entries);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        List<IdeaModel> entries = IdeaModel.listAll(IdeaModel.class, "created_at desc");

        mAdapter = new IdeaEntryAdapter(entries);
        recyclerView.setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void fabAction() {
        //@TODO: write fab action
//        Intent intent = new Intent(this.getContext(), IdeaEditActivity.class);
//        startActivity(intent);
    }

    private class IdeaEntryAdapter extends RecyclerView.Adapter<IdeaEntryAdapter.IdeaViewHolder>{

        private List<IdeaModel> ideaEntries;

        IdeaEntryAdapter(List<IdeaModel> ideaEntries){
            this.ideaEntries = ideaEntries;
        }

        class IdeaViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            TextView tvInception;
            TextView tvDate;
            ImageView imgAvatar;

            IdeaViewHolder(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.idea_list_item_tv_title);
                tvInception = (TextView) itemView.findViewById(R.id.idea_list_item_tv_inception);
                tvDate = (TextView) itemView.findViewById(R.id.idea_list_item_tv_date);
                imgAvatar = (ImageView) itemView.findViewById(R.id.idea_list_item_img_avatar);
            }
        }

        @Override
        public IdeaEntryAdapter.IdeaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.idea_list_item, parent, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // @TODO: write edit redirection
//                    Intent intent = new Intent(IdeaListFragment.this.getContext(), IdeaEditActivity.class);
//                    Bundle bundle = new Bundle();
//                    int itemPosition = recyclerView.indexOfChild(v);
//                    bundle.putLong(Constants.BUNDLE_IDEA_EDIT_ACTIVITY_ENTRY_ID, ideaEntries.get(itemPosition).getId());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            });

            return new IdeaViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(IdeaEntryAdapter.IdeaViewHolder holder, int position) {
            IdeaModel entry = ideaEntries.get(position);

            holder.tvTitle.setText(entry.title);
            holder.tvInception.setText(entry.inception);

            DateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yy");
            holder.tvDate.setText(fullDateFormat.format(entry.createdAt));

            String avatarText = entry.title.substring(0,1);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(avatarText);

            TextDrawable avatar = TextDrawable.builder()
                    .buildRound(avatarText, color);
            holder.imgAvatar.setImageDrawable(avatar);
        }

        @Override
        public int getItemCount() {
            return ideaEntries.size();
        }


    }
}
