package com.sreemenon.db;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Admin on 16/05/2017.
 */

public class DiaryModel extends SugarRecord {

    public String title;
    public Date date;
    public String entry;
    public Date createdAt;

    public DiaryModel(){}

    public DiaryModel(String title, Date date, String entry, Date createdAt) {
        this.title = title;
        this.date = date;
        this.entry = entry;
        this.createdAt = createdAt;
    }
}
