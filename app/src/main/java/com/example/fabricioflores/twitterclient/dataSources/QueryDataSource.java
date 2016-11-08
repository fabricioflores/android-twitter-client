package com.example.fabricioflores.twitterclient.dataSources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.fabricioflores.twitterclient.contracts.QueryContract;
import com.example.fabricioflores.twitterclient.helpers.QueryHelper;
import com.example.fabricioflores.twitterclient.models.Query;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class QueryDataSource {

    private SQLiteDatabase sqLiteDatabase;
    private QueryHelper queryHelper = null;
    private String[] allColumns = {QueryContract.QueryTable._ID, QueryContract.QueryTable.COLUMN_TEXT };

    public QueryDataSource(Context context){
        queryHelper = new QueryHelper(context);
    }

    public void openConnection() throws SQLException {
        sqLiteDatabase = queryHelper.getWritableDatabase();
    }

    public void closeConnection() throws SQLException {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public Query addQuery(Query query) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QueryContract.QueryTable.COLUMN_TEXT, query.getText());
        long insertId = sqLiteDatabase.insertOrThrow(QueryContract.QueryTable.TABLE_NAME, null, contentValues);
        Cursor cursor = sqLiteDatabase.query(QueryContract.QueryTable.TABLE_NAME,
                allColumns, QueryContract.QueryTable._ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Query newQuery = cursorToQuery(cursor);
        cursor.close();
        return newQuery;
    }

    private Query cursorToQuery(Cursor cursor) {
        Query query = new Query();
        query.setId(cursor.getLong(0));
        query.setText(cursor.getString(1));
        return query;
    }

    public Cursor getAllQueries(){
        String getAllTweetsQuery = "Select * from " + QueryContract.QueryTable.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(getAllTweetsQuery, null);
        return cursor;
    }
}
