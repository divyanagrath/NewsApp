package com.example.lenovo.newsapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.newsapplication.adapters.NewsDataAdapter;
import com.example.lenovo.newsapplication.models.RSSFeed;
import com.example.lenovo.newsapplication.utils.ConnectionDetector;
import com.example.lenovo.newsapplication.utils.NewsFeedParser;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsFeedParser mNewsFeeder;
    private List<RSSFeed> mRssFeedList = new ArrayList<RSSFeed>();

    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        title = getIntent().getExtras().getString("title");
        url = getIntent().getExtras().getString("url");

        getSupportActionBar().setTitle(title);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        performAsyncTask();
    }
    public void performAsyncTask() {


        if (mRssFeedList.size() > 0) {
            settingAdapter();
        } else {


            boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(getApplicationContext());

            if (isNetworkAvailable) {


                    new DoRssFeedTask().execute(url);

            }

            else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
    }





    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {

        @Override
        protected void onPreExecute() {
            Log.i("ASyncTask", "onPreExecute()");
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
            }
            mRssFeedList = mNewsFeeder.parseXmlData();

            Log.i("ASyncTask", "doInBackground()");

            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            settingAdapter();
        }

    }


    public void settingAdapter() {

        mAdapter = new NewsDataAdapter(this, mRssFeedList);
        int count = mRssFeedList.size();
        if (count != 0 && mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        }
    }

}
