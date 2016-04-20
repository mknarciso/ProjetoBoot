package com.example.android.blocodenotas.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.blocodenotas.R;
import com.example.android.blocodenotas.fragments.SettingsFragment;

/**
 * Created by Adauto on 28/03/2016.
 */
public class SettingsActivity extends AppCompatActivity{

    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            if (args != null && args.containsKey("id")) {
                long id = args.getLong("id", 0);
                if (id > 0) {
                    openFragment(SettingsFragment.newInstance(id), "Settings");
                }
            }
            openFragment(SettingsFragment.newInstance(0), "Settings");
        }
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
    @Override
    public void onBackPressed()
    {
        finish();

    }



}
