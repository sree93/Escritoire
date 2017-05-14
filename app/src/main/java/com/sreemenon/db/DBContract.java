package com.sreemenon.db;

import android.provider.BaseColumns;

/**
 * Created by Admin on 14/05/2017.
 */

public final class DBContract {

    private DBContract(){}

    private static String COLUMN_TYPE_TEXT = "TEXT";
    private static String COLUMN_TYPE_INT = "INTEGER";
    private static String COLUMN_TYPE_BOOLEAN = "BOOLEAN";
    private static String COLUMN_TYPE_DATE = "DATE";
    private static String COLUMN_TYPE_DATETIME = "DATETIME";

    public static class DiaryEntry implements BaseColumns{
        public static final String TABLE_NAME = "diary";

        public static final String COLUMN_NAME_TITLE= "title";
        public static final String COLUMN_NAME_DATE= "date";
        public static final String COLUMN_NAME_ENTRY= "entry";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";

        public static String CREATE_STATEMENT() {
            return "CREATE TABLE " + TABLE_NAME+ " (" +
                    COLUMN_NAME_TITLE+ " " + COLUMN_TYPE_TEXT + " ," +
                    COLUMN_NAME_DATE+ " " + COLUMN_TYPE_DATE + " ," +
                    COLUMN_NAME_ENTRY+ " " + COLUMN_TYPE_TEXT + " ," +
                    COLUMN_NAME_CREATED_AT+ " " + COLUMN_TYPE_DATETIME + " );";
        }

        public static String DROP_STATEMENT(){
            return "DROP TABLE IF EXISTS " + TABLE_NAME  + ";" ;
        }
    }

    public static String CREATE_STATEMENT(){
        return DiaryEntry.CREATE_STATEMENT();
    }

    public static String DROP_STATEMENT(){
        return DiaryEntry.DROP_STATEMENT();
    }


}
