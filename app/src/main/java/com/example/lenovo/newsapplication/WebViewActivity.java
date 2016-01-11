package com.example.lenovo.newsapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by lenovo on 10-01-2016.
 */
public class WebViewActivity extends ActionBarActivity {


    private WebView webView1;
    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        title = getIntent().getExtras().getString("title");
         url = getIntent().getExtras().getString("url");


        getSupportActionBar().setTitle(title);

        if (savedInstanceState != null) {
            ((WebView) findViewById(R.id.webView1)).restoreState(savedInstanceState);
        } else {

            webView1 = (WebView) findViewById(R.id.webView1);

            webView1.getSettings().setJavaScriptEnabled(true);

            webView1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


            final Activity activity = this;


            webView1.setWebViewClient(new WebViewClient()

            {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,
                                                        String url) {
                    view.loadUrl(url);
                    return true;
                }
            });

            webView1.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    activity.setProgress(progress * 1000);
                }
            });

            webView1.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                }
            });



            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView1.loadUrl(url);
                }
            });



        }


    }



    @Override
    protected void onSaveInstanceState(Bundle outState ){
        ((WebView) findViewById(R.id.webView1)).saveState(outState);
    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView1.canGoBack()) {
            webView1.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
