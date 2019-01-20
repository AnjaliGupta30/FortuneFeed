package com.example.asus.fortunefeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 14-01-2019.
 */

public class Articles {

    @SerializedName("articles")
    private ArrayList<News> articles;

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }
}
