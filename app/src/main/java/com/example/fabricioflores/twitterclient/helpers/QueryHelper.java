package com.example.fabricioflores.twitterclient.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fabricioflores.twitterclient.contracts.QueryContract;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class QueryHelper extends SQLiteOpenHelper {
    private final String TAG = QueryHelper.class.getSimpleName();

    public QueryHelper(Context context){
        super(context, QueryContract.DATABASE_NAME,null, QueryContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QueryContract.QueryTable.CREATE_TABLE);
        Log.d(TAG,QueryContract.QueryTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(QueryContract.QueryTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
        Log.d(TAG, QueryContract.QueryTable.DELETE_TABLE);
    }
}
