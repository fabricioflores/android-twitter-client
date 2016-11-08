package com.example.fabricioflores.twitterclient.contracts;

import android.provider.BaseColumns;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class QueryContract {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "twitter.db";
    public static final String TEXT_TYPE = " TEXT ";
    public static final String COMMA_SEP = " , ";

    private QueryContract(){}

    public static class QueryTable implements BaseColumns {
        public static final String TABLE_NAME = "queries";
        public static final String COLUMN_TEXT = "text";

        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP +
                COLUMN_TEXT + TEXT_TYPE + " );";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
