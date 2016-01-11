package com.example.lenovo.newsapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.lenovo.newsapplication.NewsActivity;
import com.example.lenovo.newsapplication.R;
import com.example.lenovo.newsapplication.models.NewsPaper;

import java.util.ArrayList;

/**
 * Created by lenovo on 10-01-2016.
 */
public class NewsPaperAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NewsPaper> newsPapers = new ArrayList<>();

    public NewsPaperAdapter(Context context, ArrayList<NewsPaper> newsPapers) {
        this.context = context;
        this.newsPapers = newsPapers;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

            gridView = inflater.inflate(R.layout.grid_item, null);

            Button btn = (Button) gridView
                    .findViewById(R.id.btn_news_paper);
            btn.setText(newsPapers.get(position).getPaperName());
            final Intent newsIntent = new Intent(context, NewsActivity.class);

            newsIntent.putExtra("title", newsPapers.get(position).getPaperName());

            newsIntent.putExtra("url", newsPapers.get(position).getUrl());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    v.getContext().startActivity(newsIntent);
                }
            });


        return gridView;
    }

    @Override
    public int getCount() {
        return newsPapers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
