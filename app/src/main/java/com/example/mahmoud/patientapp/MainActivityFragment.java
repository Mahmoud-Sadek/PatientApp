package com.example.mahmoud.patientapp;

import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    DatabaseAdapter databaseAdapter;
    static ArrayList<PatientData> patientData;
    static String state="first" ;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        databaseAdapter = new DatabaseAdapter(getContext());
        if (state.equals("first"))
            patientData = databaseAdapter.getAllData();

        PatientAdapter patientAdapter = new PatientAdapter(getContext(), patientData);


        GridView patientGridView = (GridView) rootView.findViewById(R.id.patientGridView);
        patientGridView.setAdapter(patientAdapter);
        patientGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent patientDetail = new Intent(getActivity(), DetailOfPatient.class);
                DetailOfPatient.patientData = patientData.get(i);
                startActivity(patientDetail);
            }
        });
        return rootView;
    }
}
