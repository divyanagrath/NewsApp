package com.example.lenovo.newsapplication.models;

/**
 * Created by lenovo on 10-01-2016.
 */
public class NewsPaper {
    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    private String paperName;
    private String Url;

}
