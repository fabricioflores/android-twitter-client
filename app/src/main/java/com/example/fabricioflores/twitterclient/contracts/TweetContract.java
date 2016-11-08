package com.example.fabricioflores.twitterclient.contracts;

import android.provider.BaseColumns;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class TweetContract {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "twitter.db";
    public static final String TEXT_TYPE = " TEXT ";
    public static final String INTEGER_TYPE = " INTEGER ";
    public static final String COMMA_SEP = " , ";

    private TweetContract(){}

    public static class TweetTable implements BaseColumns {
        public static final String TABLE_NAME = "tweets";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_USERNAME= "username";
        public static final String COLUMN_USERIMAGE= "userimage";
        public static final String COLUMN_QUERY_ID= "query_id";

        public static final String CREATE_TABLE = "CREATE TABLE "+
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP +
                COLUMN_TEXT + TEXT_TYPE + COMMA_SEP +
                COLUMN_USERIMAGE + TEXT_TYPE + COMMA_SEP +
                COLUMN_QUERY_ID + INTEGER_TYPE + COMMA_SEP +
                COLUMN_USERNAME + TEXT_TYPE + " );";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
