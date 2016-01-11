package com.example.lenovo.newsapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.lenovo.newsapplication.adapters.NewsPaperAdapter;
import com.example.lenovo.newsapplication.models.NewsPaper;

import java.util.ArrayList;

/**
 * Created by lenovo on 10-01-2016.
 */
public class MainActivity extends Activity {

    GridView gridView;
    private NewsPaper newsObjct = new NewsPaper();

    static final ArrayList<NewsPaper> newspaper = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView1);

        newsObjct.setPaperName("THE TIMES OF INDIA");
        newsObjct.setUrl("http://timesofindia.feedsportal.com/c/33039/f/533965/index.rss");
        newspaper.add(0, newsObjct);

        newsObjct = new NewsPaper();
        newsObjct.setPaperName("ECONOMICS TIMES");
        newsObjct.setUrl("http://economictimes.indiatimes.com/News/rssfeeds/1715249553.cms");
        newspaper.add(1, newsObjct);

        newsObjct = new NewsPaper();
        newsObjct.setPaperName("FORBES INDIA");
        newsObjct.setUrl("http://forbesindia.com/rssfeed/rss_all.xml");
        newspaper.add(2, newsObjct);

        newsObjct = new NewsPaper();
        newsObjct.setPaperName("THE HINDU");
        newsObjct.setUrl("http://www.thehindu.com/news/?service=rss");
        newspaper.add(3, newsObjct);

        newsObjct = new NewsPaper();
        newsObjct.setPaperName("TELEGRAPH");
        newsObjct.setUrl("http://www.telegraphindia.com/feeds/rss.jsp?id=4");
        newspaper.add(4, newsObjct);

        newsObjct = new NewsPaper();
        newsObjct.setPaperName("INDIA TODAY");
        newsObjct.setUrl("http://indiatoday.feedsportal.com/c/33614/f/589702/index.rss?http://indiatoday.intoday.in/rss/article.jsp?sid=36");
        newspaper.add(5,newsObjct);

        gridView.setAdapter(new NewsPaperAdapter(this, newspaper));


    }

    @Override
    protected void onStop() {
        super.onStop();
        newspaper.clear();
    }
}
