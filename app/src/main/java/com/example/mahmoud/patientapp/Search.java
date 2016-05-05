package com.example.mahmoud.patientapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    DatabaseAdapter databaseAdapter;
    int hour, minute;
    int yr, month, day;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 0;
    EditText TimeText;
    EditText DateText;
    EditText PatientName;
    EditText Medicine;
    EditText Disease;
    Button SearchButton;
    Button CancelButton;

    String patientNameStr;
    String timeStr;
    String dateSrr;
    String medicineStr;
    String diseaseStr;
    PatientData patientData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PatientName = (EditText) findViewById(R.id.patientNameEditText);
        Medicine = (EditText) findViewById(R.id.medicineEditText);
        Disease = (EditText) findViewById(R.id.diseaseEditText);
        TimeText = (EditText) findViewById(R.id.AppoientTimeFromEditText);
        DateText = (EditText) findViewById(R.id.AppoientDateFromEditText);
        SearchButton = (Button) findViewById(R.id.search);
        CancelButton = (Button) findViewById(R.id.cancelButton);


        TimeText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(TIME_DIALOG_ID);
                return false;
            }
        });
        DateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientNameStr = PatientName.getText().toString();
                timeStr = hour + ":" + minute;
                dateSrr = (month + 1) + "/" + day + "/" + yr;
                medicineStr = Medicine.getText().toString();
                diseaseStr = Disease.getText().toString();
                patientData = new PatientData();
                patientData.setPatientName(patientNameStr);
                patientData.setAppointmentTime(timeStr);
                patientData.setAppointmentDate(dateSrr);
                patientData.setMedicine(medicineStr);
                patientData.setDisease(diseaseStr);

                databaseAdapter = new DatabaseAdapter(getBaseContext());
                MainActivityFragment.patientData = databaseAdapter.search(patientData);
                MainActivityFragment.state = "second";
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(
                        this, mTimeSetListener, hour, minute, false);
        }
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(
                        this, mDateSetListener, yr, month, day);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener()
            {
                public void onTimeSet(
                        TimePicker view, int hourOfDay, int minuteOfHour)
                {
                    hour = hourOfDay;
                    minute = minuteOfHour;
                    TimeText.setText(hour + ":" + minute);
                    Toast.makeText(getBaseContext(), "Time selected: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
                }
            };

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener()
            {
                public void onDateSet(
                        DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    yr = year;
                    month = monthOfYear;
                    day = dayOfMonth;
                    DateText.setText((month + 1) +
                            "/" + day + "/" + yr);
                    Toast.makeText(getBaseContext(),
                            "You have selected : " + (month + 1) +
                                    "/" + day + "/" + yr,
                            Toast.LENGTH_SHORT).show();
                }
            };
}
