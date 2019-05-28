package com.java.ny.data.model;

import com.google.gson.annotations.SerializedName;

public class NYTimesVO {

    public final long id;
    public final String title;
    public final String published_date;
    public final String  byline;


    public NYTimesVO(long id, String title, String published_date, String byline) {
        this.id = id;
        this.title = title;
        this.published_date = published_date;
        this.byline = byline;
    }
}
