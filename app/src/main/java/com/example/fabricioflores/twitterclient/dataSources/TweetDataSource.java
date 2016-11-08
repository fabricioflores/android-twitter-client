package com.example.fabricioflores.twitterclient.dataSources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.fabricioflores.twitterclient.contracts.TweetContract;
import com.example.fabricioflores.twitterclient.helpers.TweetHelper;
import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class TweetDataSource {

    private SQLiteDatabase sqLiteDatabase;
    private TweetHelper tweetHelper = null;

    public TweetDataSource(Context context){
        tweetHelper = new TweetHelper(context);
    }

    public void openConnection() throws SQLException {
        sqLiteDatabase = tweetHelper.getWritableDatabase();
    }

    public void closeConnection() throws SQLException {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void addTweet(Tweet tweet, long queryId) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TweetContract.TweetTable.COLUMN_TEXT, tweet.text);
        contentValues.put(TweetContract.TweetTable.COLUMN_USERNAME, tweet.user.name);
        contentValues.put(TweetContract.TweetTable.COLUMN_USERIMAGE, tweet.user.profileImageUrl);
        contentValues.put(TweetContract.TweetTable.COLUMN_QUERY_ID, queryId);
        sqLiteDatabase.insertOrThrow(TweetContract.TweetTable.TABLE_NAME, null, contentValues);
    }

    public Cursor getAllTweets(String selection){
        String getAllTweetsQuery = "Select * from " + TweetContract.TweetTable.TABLE_NAME + " where " + selection;
        return sqLiteDatabase.rawQuery(getAllTweetsQuery, null);
    }
}
