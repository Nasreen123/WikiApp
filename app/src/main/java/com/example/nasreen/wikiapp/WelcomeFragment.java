package com.example.nasreen.wikiapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nasreen on 2/13/18.
 */

public class WelcomeFragment extends Fragment {
    FragmentActivity listener;
    OnUserRequestCallback onUserRequestCallback;

    public interface OnUserRequestCallback {
        public void randomArticleRequested();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
        onUserRequestCallback = (OnUserRequestCallback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // called before views are created.  activity might not be fully created at this point
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // fragment creates it's view object hierarchy
        View view = inflater.inflate(R.layout.welcome_fragment, parent, false);

        final Button randomButton = (Button) view.findViewById(R.id.randomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onUserRequestCallback.randomArticleRequested();
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // called soon after onCreateView, if a view is returned

    }

    @Override
    public void onDetach() {
        // null out references to prevent memory leaks when we are finished with the fragment
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
