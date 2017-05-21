package com.sreemenon.escritoire;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by srinath on 21/5/17.
 */

public abstract class MyFragment extends Fragment implements OnActvityInteractionListener{

    private int fabIconDrawable;
    private int layout;
    OnFragmentInteractionListener mainActivity;

    abstract void initUIElements(View view);


    MyFragment(int layoutResource, int fabIconDrawable){
        this.fabIconDrawable = fabIconDrawable;
        this.layout = layoutResource;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(layout, container, false);

        initUIElements(view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mainActivity = (OnFragmentInteractionListener) context;
            mainActivity.setFabIcon(fabIconDrawable);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
}
