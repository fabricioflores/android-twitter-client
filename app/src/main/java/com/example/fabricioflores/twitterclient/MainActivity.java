package com.example.fabricioflores.twitterclient;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabricioflores.twitterclient.dataSources.QueryDataSource;
import com.example.fabricioflores.twitterclient.dataSources.TweetDataSource;
import com.example.fabricioflores.twitterclient.models.Query;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends ListActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Q8Dt86zMMLTqwrOdzGxCuWGcl";
    private static final String TWITTER_SECRET = "rs4KZMgnsiqJgp8AxqcFDwoZawcmlLLAJ7xzAPlaIK3ayZiJTM";
    private Callback<TimelineResult<Tweet>> callback;
    TweetDataSource tweetDataSource = null;
    QueryDataSource queryDataSource = null;
    private String mainQuery = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        tweetDataSource = new TweetDataSource(this);
        queryDataSource = new QueryDataSource(this);
        callback = new Callback<TimelineResult<Tweet>>() {

            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                Query query = new Query();
                query.setText(mainQuery);
                queryDataSource.openConnection();
                Query savedQuery = queryDataSource.addQuery(query);
                queryDataSource.closeConnection();

                Object[] tweets = result.data.items.toArray();
                for(Object tweet: tweets){
                    Tweet toInsert = (Tweet) tweet;
                    tweetDataSource.openConnection();
                    tweetDataSource.addTweet(toInsert, savedQuery.getId());
                    tweetDataSource.closeConnection();
                }
            }

            @Override
            public void failure(TwitterException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    public void search(View view){
        EditText editText = (EditText) findViewById(R.id.inputSearch);
        mainQuery = editText.getText().toString();
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query(mainQuery)
                .build();
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(searchTimeline)
                .build();
        setListAdapter(adapter);
        searchTimeline.next(null, callback);
    }
}
