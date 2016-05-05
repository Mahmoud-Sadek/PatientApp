package com.example.mahmoud.patientapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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

public class AddAppointment extends AppCompatActivity {

    int hour, minute;
    int yr, month, day;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 0;
    EditText TimeText;
    EditText DateText;
    EditText PatientName;
    EditText Telephone;
    EditText Email;
    EditText Medicine;
    EditText Disease;
    EditText Cost;
    Button SaveButton;
    Button CancelButton;

    String patientNameStr;
    String telephoneStr;
    String emailStr;
    String timeStr;
    String dateSrr;
    String medicineStr;
    String diseaseStr;
    String costStr;
    PatientData patientData;
    DatabaseAdapter databaseAdapter;
    String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
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

        state = getIntent().getStringExtra("key");

        PatientName = (EditText) findViewById(R.id.patientNameEditText);
        Telephone = (EditText) findViewById(R.id.patientTeleEditText);
        Email = (EditText) findViewById(R.id.patientEmailEditText);
        Medicine = (EditText) findViewById(R.id.medicineEditText);
        Disease = (EditText) findViewById(R.id.diseaseEditText);
        Cost = (EditText) findViewById(R.id.costEditText);
        TimeText = (EditText) findViewById(R.id.AppoientTimeEditText);
        DateText = (EditText) findViewById(R.id.AppoientDateEditText);
        SaveButton = (Button) findViewById(R.id.save);
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
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientNameStr = PatientName.getText().toString();
                telephoneStr = Telephone.getText().toString();
                emailStr = Email.getText().toString();
                timeStr = hour + ":" + minute;
                dateSrr = (month + 1) + "/" + day + "/" + yr;
                medicineStr = Medicine.getText().toString();
                diseaseStr = Disease.getText().toString();
                costStr = Cost.getText().toString();
                patientData = new PatientData();
                patientData.setPatientName(patientNameStr);
                patientData.setTelephone(telephoneStr);
                patientData.setEmail(emailStr);
                patientData.setAppointmentTime(timeStr);
                patientData.setAppointmentDate(dateSrr);
                patientData.setMedicine(medicineStr);
                patientData.setDisease(diseaseStr);
                patientData.setCost(costStr);


                databaseAdapter = new DatabaseAdapter(getBaseContext());
                if(state.equals("add")) {
                    databaseAdapter.insertData(patientData);
                }else {
                    patientData.setID(DetailOfPatient.patientData.getID());
                    databaseAdapter.UpdateData(DetailOfPatient.patientData,patientData);
                }

                finish();
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
                    Toast.makeText(getBaseContext(),"Time selected: " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
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
