package com.example.android.blocodenotas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.blocodenotas.R;

/**
 * Created by Adauto on 28/03/2016.
 */

/**
 * Created by Adauto on 25/03/2016.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View mRootView;

    public static SettingsFragment newInstance(long id) {
        SettingsFragment fragment = new SettingsFragment();
        if (id > 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", id);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_settings, container, false);
        Button buttonExport = (Button) mRootView.findViewById(R.id.export_button);
        buttonExport.setOnClickListener(this);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.export_button):
                //Inserir método de exportação
                break;
        }
    }
}
