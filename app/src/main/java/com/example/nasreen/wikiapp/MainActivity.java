package com.example.nasreen.wikiapp;

import android.app.DownloadManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnUserRequestCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWelcomeFragment();
    }

    public void loadWelcomeFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new WelcomeFragment());
        ft.commit();
    }

    public void loadArticleFragment(String responseText) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("articleText", responseText);

        ArticleFragment  articleFragment = new ArticleFragment();
        articleFragment.setArguments(bundle);

        ft.replace(R.id.placeholder, articleFragment);
        ft.commit();

    }

    @Override
    public void randomArticleRequested() {
        final TextView mTextView = (TextView) findViewById(R.id.textView2);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://en.wikipedia.org/w/api.php?action=query&titles=Main%20Page&prop=revisions&rvprop=content&format=jsonfm&formatversion=2";
                //"https://en.wikipedia.org/wiki/Special:Random";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                        loadArticleFragment(response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);

        // TODO:
        // deal with json response object
        // fix request url
        // load fragment then send it the response when received?

        //loadArticleFragment();
    }
}
