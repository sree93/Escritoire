package com.sreemenon.escritoire;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sreemenon.protean.ProteanRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link EditIdeaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditIdeaFragment extends MyFragment {

    private ProteanRecyclerView rv;

    //private OnFragmentInteractionListener mainActivity;

    public EditIdeaFragment() {
        super(R.layout.fragment_edit_idea,R.drawable.ic_add_black_24dp);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditIdeaFragment.
     */
    public static EditIdeaFragment newInstance() {
        EditIdeaFragment fragment = new EditIdeaFragment();
        return fragment;
    }

    @Override
    public void fabAction() {

    }

    @Override
    void initUIElements(View view) {
        rv = (ProteanRecyclerView) view.findViewById(R.id.dna_rv);
        rv.setRichText("<p>Hello this works!</p>\n<p>This works too!</p>");
    }
}
