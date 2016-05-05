package com.example.mahmoud.patientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailOfPatient extends AppCompatActivity {

    static PatientData patientData;
    TextView TimeText;
    TextView DateText;
    TextView PatientName;
    TextView Telephone;
    TextView Email;
    TextView Medicine;
    TextView Disease;
    TextView Cost;
    Button Edit;
    Button Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_patient);
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

        PatientName = (TextView) findViewById(R.id.patientNameText);
        Telephone = (TextView) findViewById(R.id.patientTeleText);
        Email = (TextView) findViewById(R.id.patientEmailText);
        Medicine = (TextView) findViewById(R.id.medicineText);
        Disease = (TextView) findViewById(R.id.diseaseText);
        Cost = (TextView) findViewById(R.id.costText);
        DateText = (TextView) findViewById(R.id.AppoientDateText);
        Edit = (Button) findViewById(R.id.editButton);
        Delete = (Button) findViewById(R.id.deleteButton);

        PatientName.setText(patientData.getPatientName());
        Telephone.setText(patientData.getTelephone());
        Email.setText(patientData.getEmail());
        DateText.setText(patientData.getAppointmentTime()+
        patientData.getAppointmentDate());
        Medicine.setText(patientData.getMedicine());
        Disease.setText(patientData.getDisease());
        Cost.setText(patientData.getCost());

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getBaseContext(), AddAppointment.class);
                edit.putExtra("key", "edit");
                startActivity(edit);
                finish();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAdapter databaseAdapter = new DatabaseAdapter(getBaseContext());
                databaseAdapter.DeleteData(patientData);
                Toast.makeText(DetailOfPatient.this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
