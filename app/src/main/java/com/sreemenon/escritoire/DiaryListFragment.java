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
import com.sreemenon.db.DiaryModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link DiaryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryListFragment extends MyFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public DiaryListFragment() {
        super(R.layout.fragment_diary_list, R.drawable.ic_add_black_24dp);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static DiaryListFragment newInstance() {
        DiaryListFragment fragment = new DiaryListFragment();
        return fragment;
    }

    @Override
    void initUIElements(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.frag_diary_list_recycler_view);
        recyclerView.hasFixedSize();

        mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        List<DiaryModel> entries = DiaryModel.listAll(DiaryModel.class, "date desc");

        mAdapter = new DiaryEntryAdapter(entries);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        List<DiaryModel> entries = DiaryModel.listAll(DiaryModel.class, "date desc");

        mAdapter = new DiaryEntryAdapter(entries);
        recyclerView.setAdapter(mAdapter);
        super.onResume();
    }

    @Override
    public void fabAction() {
        Intent intent = new Intent(this.getContext(), DiaryEditActivity.class);
        startActivity(intent);
    }

    private class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryViewHolder>{

        private List<DiaryModel> diaryEntries;

        DiaryEntryAdapter(List<DiaryModel> diaryEntries){
            this.diaryEntries = diaryEntries;
        }

        class DiaryViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;
            TextView tvDate;
            ImageView imgAvatar;

            DiaryViewHolder(View itemView) {
                super(itemView);
                tvTitle = (TextView) itemView.findViewById(R.id.diary_list_item_tv_title);
                tvDate = (TextView) itemView.findViewById(R.id.diary_list_item_tv_date);
                imgAvatar = (ImageView) itemView.findViewById(R.id.diary_list_item_img_avatar);
            }
        }

        @Override
        public DiaryEntryAdapter.DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.diary_list_item, parent, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DiaryListFragment.this.getContext(), DiaryEditActivity.class);
                    Bundle bundle = new Bundle();
                    int itemPosition = recyclerView.indexOfChild(v);
                    bundle.putLong(Constants.BUNDLE_DIARY_EDIT_ACTIVITY_ENTRY_ID, diaryEntries.get(itemPosition).getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            return new DiaryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(DiaryEntryAdapter.DiaryViewHolder holder, int position) {
            DiaryModel entry = diaryEntries.get(position);

            holder.tvTitle.setText(entry.title);

            DateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            holder.tvDate.setText(fullDateFormat.format(entry.date));


            DateFormat dayDateFormat = new SimpleDateFormat("dd");
            String dayOfMonth = dayDateFormat.format(entry.date);

            ColorGenerator generator = ColorGenerator.MATERIAL; 
            int color = generator.getColor(dayOfMonth);

            TextDrawable avatar = TextDrawable.builder()
                    .buildRound(dayOfMonth, color);
            holder.imgAvatar.setImageDrawable(avatar);
        }

        @Override
        public int getItemCount() {
            return diaryEntries.size();
        }


    }
}
