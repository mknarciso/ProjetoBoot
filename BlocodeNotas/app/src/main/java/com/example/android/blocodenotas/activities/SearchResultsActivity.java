package com.example.android.blocodenotas.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.android.blocodenotas.R;
import com.example.android.blocodenotas.fragments.NoteListSearchFragment;

public class SearchResultsActivity extends AppCompatActivity {

    private Toolbar searchToolbar;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent myIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(myIntent.getAction())) {
            query = myIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        }

        searchToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        searchToolbar.setTitle(query);
        setSupportActionBar(searchToolbar);

        NoteListSearchFragment searchFragment = new NoteListSearchFragment();
        searchFragment.setQuery(query);
        openFragment(searchFragment, "Search");
    }

    private void openFragment(final Fragment fragment, String title){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(title);

    }

    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}
