package com.example.mahmoud.patientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 5/2/2016.
 */
public class PatientAdapter extends BaseAdapter {

    Context context;
    ArrayList<PatientData> patientData;
    public PatientAdapter(Context context, ArrayList<PatientData> patientData) {
        this.context = context;
        this.patientData = patientData;
    }

    @Override
    public int getCount() {
        return patientData.size();
    }

    @Override
    public Object getItem(int i) {
        return patientData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null ){
            view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.patient_list_item,viewGroup,false);
        }
        TextView patientItem = (TextView) view.findViewById(R.id.patient_item);
        patientItem.setText(patientData.get(i).getPatientName());
        return view;
    }
}
