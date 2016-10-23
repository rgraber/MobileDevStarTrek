package com.example.sse.customlistview_sse;

/**
 * Created by rebeccagraber on 10/22/16.
 */

public class Episode {

    private String name;
    private String desc;
    private int drawable;
    private String imdb;
    private String pref_key;

    public Episode(String name, String desc, int draw, String imdb, String pref_key)
    {
        this.name = name;
        this.desc = desc;
        this.drawable = draw;
        this.imdb = imdb;
        this.pref_key = pref_key;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getImdb() {
        return imdb;
    }

    public String getPref_key() {
        return pref_key;
    }
}
