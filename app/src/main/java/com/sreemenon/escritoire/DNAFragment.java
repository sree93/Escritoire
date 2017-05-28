package com.sreemenon.escritoire;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sreemenon.protean.ProteanRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link DNAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DNAFragment extends MyFragment {

    private ProteanRecyclerView rv;

    //private OnFragmentInteractionListener mainActivity;

    public DNAFragment() {
        super(R.layout.fragment_dna,R.drawable.ic_add_black_24dp);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DNAFragment.
     */
    public static DNAFragment newInstance() {
        DNAFragment fragment = new DNAFragment();
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
