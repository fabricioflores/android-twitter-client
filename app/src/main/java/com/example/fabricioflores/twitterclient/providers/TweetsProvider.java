package com.example.fabricioflores.twitterclient.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.fabricioflores.twitterclient.dataSources.TweetDataSource;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class TweetsProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "com.fabricioflores.tweetProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/tweets");
    private static final int TWEETS = 1;
    private static final UriMatcher uriMatcher = getUriMatcher();
    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "tweets", TWEETS);
        return uriMatcher;
    }
    private TweetDataSource tweetDataSource;

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TWEETS:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME + ".provider.tweets";
        }
        return "";
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        tweetDataSource = new TweetDataSource(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        tweetDataSource.openConnection();
        return tweetDataSource.getAllTweets();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
