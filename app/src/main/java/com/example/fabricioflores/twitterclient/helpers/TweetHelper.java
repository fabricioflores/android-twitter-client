package com.example.fabricioflores.twitterclient.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fabricioflores.twitterclient.contracts.TweetContract;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class TweetHelper extends SQLiteOpenHelper {
    private final String TAG = TweetHelper.class.getSimpleName();

    public TweetHelper(Context context){
        super(context, TweetContract.DATABASE_NAME,null, TweetContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TweetContract.TweetTable.CREATE_TABLE);
        Log.d(TAG,TweetContract.TweetTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(TweetContract.TweetTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
        Log.d(TAG, TweetContract.TweetTable.DELETE_TABLE);
    }
}
