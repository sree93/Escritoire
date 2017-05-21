package com.sreemenon.escritoire;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link DNAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DNAFragment extends MyFragment {

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

    }
}
