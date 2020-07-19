package com.example.newsapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.example.newsapp.adaptermodel;
import com.example.newsapp.data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {


    boolean isloaded=false;
    private static final String ARG_SECTION_NUMBER = "section_number";
    List<data> list = new ArrayList<data>();
   adaptermodel jz;
    String key = "9d94abae7d474e3eb28840fee0fcb8b8";
    String source="the-times-of-india";
    private PageViewModel pageViewModel;


    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isloaded) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://newsapi.org/v2/top-headlines?sources=" + source + "&apiKey=9d94abae7d474e3eb28840fee0fcb8b8", new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            JSONObject j = response;
                          //  Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();;
                            JSONArray jsonarray = null;
                            try {
                                jsonarray = j.getJSONArray("articles");
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                    String heading = jsonobject.getString("title");
                                    String url = jsonobject.getString("urlToImage");
                                    String title = jsonobject.getString("description");
                                    String website = jsonobject.getString("url");
                                    String timings = jsonobject.getString("publishedAt");
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                    DateFormat Format = new SimpleDateFormat("HH:mm:ss");
                                    String date = timings.substring(0,10);
                                    String time = timings.substring(11,19);
                                    data model = new data(url, title, time, date, heading, website);
                                    if (url != null) {
                                        list.add(model);
                              //          Toast.makeText(getContext(),"added",Toast.LENGTH_LONG).show();
                                        Log.d("DATA",model.getHeading()+ "    "+ model.getThumbnail());
                                        jz.notifyDataSetChanged();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            isloaded=true;
                        }
                    }
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragmentlay, container, false);
        RecyclerView recycle = v.findViewById(R.id.recycle);
        jz = new adaptermodel(list);
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setAdapter(jz);

        recycle.setLayoutManager(lm);
        jz.notifyDataSetChanged();
        return  v;
    }

    public PlaceholderFragment(String k) {
        this.source = k;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        jz = new adaptermodel(list);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
}