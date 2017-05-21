package com.sreemenon.escritoire;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sreemenon.api.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends MyFragment {

    private TextView tvQuoteText;
    private TextView tvQuoteAuthor;


    public HomeFragment() {
        super(R.layout.fragment_home, R.drawable.ic_refresh_black_24px);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    void initUIElements(View view) {
        tvQuoteAuthor = (TextView) view.findViewById(R.id.frag_home_tv_quote_author);
        tvQuoteText = (TextView) view.findViewById(R.id.frag_home_tv_quote_text);

        QuoteTask quoteTask = new QuoteTask();
        quoteTask.execute();
    }

    @Override
    public void fabAction() {
        //@TODO: add home fab action
    }

    private class QuoteTask extends AsyncTask<Void, Void, Void>{

        JSONArray jsonResponse = null;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler handler = new HttpHandler();
            try {
                jsonResponse = handler.makeServiceCallJsonArray(HomeFragment.this.getString(R.string.quote_api_url));
            }catch (Exception exc){
                exc.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                tvQuoteText.setText(Html.fromHtml(jsonResponse.getJSONObject(0).getString("content").replace("\n", "").trim()));
                tvQuoteAuthor.setText(Html.fromHtml(jsonResponse.getJSONObject(0).getString("title").replace("\n", "").trim()));
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }

            super.onPostExecute(aVoid);
        }
    }
}
