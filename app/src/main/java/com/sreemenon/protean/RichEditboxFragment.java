package com.sreemenon.protean;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sreemenon.escritoire.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RichEditboxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RichEditboxFragment extends Fragment {
    private static final String ARG_RICHTEXT = "RICHTEXT";

    private String richText;

    public RichEditboxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param richText the formatted rich text or empty strings
     *                 for a new Editbox.
     * @return A new instance of fragment RichEditboxFragment.
     */
    public static RichEditboxFragment newInstance(String richText) {
        RichEditboxFragment fragment = new RichEditboxFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RICHTEXT, richText);
        fragment.setArguments(args);
        return fragment;
    }
    public static RichEditboxFragment newInstance() {
        return newInstance("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            refreshArgRichtext();
        }
    }

    private String refreshArgRichtext(){
        richText = getArguments().getString(ARG_RICHTEXT);
        return richText;
    }

    private void setArgRichtext(String richText){
        this.richText = richText;
        getArguments().putString(ARG_RICHTEXT, richText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
}
