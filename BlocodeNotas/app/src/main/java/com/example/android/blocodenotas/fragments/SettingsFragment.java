package com.example.android.blocodenotas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.android.blocodenotas.R;

/**
 * Created by Adauto on 28/03/2016.
 */

/**
 * Created by Adauto on 25/03/2016.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    Button buttonExport;
    Button buttonOrder;
    RadioGroup ordenationTypes;
    int selected_id;


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
        buttonExport = (Button) mRootView.findViewById(R.id.export_button);
        buttonOrder = (Button) mRootView.findViewById(R.id.order_button);
        buttonExport.setOnClickListener(this);
        buttonOrder.setOnClickListener(this);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.export_button):
                //Inserir método de exportação
                break;
            case(R.id.order_button):
                ordenationTypes = (RadioGroup)mRootView.findViewById(R.id.order_type);
                selected_id = ordenationTypes.getCheckedRadioButtonId();
                if(selected_id == R.id.button_order_date_created) {
                    //Inserir método de ordenação por data criada
                }
                else if(selected_id == R.id.button_order_date_modified) {
                    //Inserir método de ordenação por data modificada
                }
                else if(selected_id == R.id.button_order_title) {
                    //Inserir método de ordenação por nome
                }
        }
    }
}
