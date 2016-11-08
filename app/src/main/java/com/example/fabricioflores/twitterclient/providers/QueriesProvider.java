package com.example.fabricioflores.twitterclient.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.fabricioflores.twitterclient.dataSources.QueryDataSource;

/**
 * Created by fabricioflores on 5/11/16.
 */

public class QueriesProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "com.fabricioflores.queryProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/queries");
    private static final int QUERIES = 2;
    private static final UriMatcher uriMatcher = getUriMatcher();
    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "queries", QUERIES);
        return uriMatcher;
    }
    private QueryDataSource queryDataSource;

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case QUERIES:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME + ".provider.queries";
        }
        return "";
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        queryDataSource = new QueryDataSource(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        queryDataSource.openConnection();
        return queryDataSource.getAllQueries();
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
