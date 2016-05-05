package com.example.mahmoud.patientapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 5/1/2016.
 */
public class DatabaseAdapter {
    DatabaseHelper databaseHelper;
    Context context;

    public DatabaseAdapter(Context context) {
        databaseHelper = new DatabaseHelper(context);
        this.context = context;
    }


    public long insertData(PatientData patientData) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PATIENT_NAME, patientData.getPatientName());
        contentValues.put(DatabaseHelper.TELEPHONE, patientData.getTelephone());
        contentValues.put(DatabaseHelper.EMAIL, patientData.getEmail());
        contentValues.put(DatabaseHelper.DATE, patientData.getAppointmentDate());
        contentValues.put(DatabaseHelper.TIME, patientData.getAppointmentTime());
        contentValues.put(DatabaseHelper.MEDICINE, patientData.getMedicine());
        contentValues.put(DatabaseHelper.DISEASE, patientData.getDisease());
        contentValues.put(DatabaseHelper.COST, patientData.getCost());

        long id = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public ArrayList<PatientData> getAllData() {
        int i = 0;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {DatabaseHelper.UID, DatabaseHelper.PATIENT_NAME, DatabaseHelper.TELEPHONE, DatabaseHelper.EMAIL, DatabaseHelper.DATE, DatabaseHelper.TIME, DatabaseHelper.MEDICINE, DatabaseHelper.DISEASE, DatabaseHelper.COST};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<PatientData> patientData =  new ArrayList<PatientData>();
        while (cursor.moveToNext()) {
            int ID = cursor.getInt(0);
            String PATIENT_NAME = cursor.getString(1);
            String TELEPHONE = cursor.getString(2);
            String EMAIL = cursor.getString(3);
            String TIME = cursor.getString(4);
            String DATE = cursor.getString(5);
            String MEDICINE = cursor.getString(6);
            String DISEASE = cursor.getString(7);
            String COST = cursor.getString(8);
            patientData.add(new PatientData());
            patientData.get(i).setID(ID);
            patientData.get(i).setPatientName(PATIENT_NAME);
            patientData.get(i).setEmail(EMAIL);
            patientData.get(i).setAppointmentDate(DATE);
            patientData.get(i).setTelephone(TELEPHONE);
            patientData.get(i).setAppointmentTime(TIME);
            patientData.get(i).setAppointmentDate(DATE);
            patientData.get(i).setDisease(DISEASE);
            patientData.get(i).setMedicine(MEDICINE);
            patientData.get(i).setCost(COST);
            i++;
        }
        return patientData;
    }
    public int UpdateData(PatientData OldPatientData, PatientData NewPatientData){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues contentValuse=new ContentValues();
        contentValuse.put(DatabaseHelper.PATIENT_NAME, NewPatientData.getPatientName());
        contentValuse.put(DatabaseHelper.TELEPHONE, NewPatientData.getTelephone());
        contentValuse.put(DatabaseHelper.EMAIL, NewPatientData.getEmail());
        contentValuse.put(DatabaseHelper.TIME, NewPatientData.getAppointmentTime());
        contentValuse.put(DatabaseHelper.DATE, NewPatientData.getAppointmentDate());
        contentValuse.put(DatabaseHelper.MEDICINE, NewPatientData.getMedicine());
        contentValuse.put(DatabaseHelper.DISEASE, NewPatientData.getDisease());
        contentValuse.put(DatabaseHelper.COST, NewPatientData.getCost());
        String whereArgs[]={OldPatientData.getID()+""};
        int count=db.update(DatabaseHelper.TABLE_NAME, contentValuse,
                DatabaseHelper.UID + " =? " , whereArgs);
//        Toast.makeText(context, count, Toast.LENGTH_SHORT).show();
        return count;
    }

    public int DeleteData(PatientData patientData){

        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        String  whereArgs[]={patientData.getID()+""};
        int count=db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.UID + " =?", whereArgs);
        return count;

    }

    public ArrayList<PatientData> search(PatientData searchPatientData) {
        int i = 0;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {DatabaseHelper.UID, DatabaseHelper.PATIENT_NAME, DatabaseHelper.TELEPHONE, DatabaseHelper.EMAIL, DatabaseHelper.DATE, DatabaseHelper.TIME, DatabaseHelper.MEDICINE, DatabaseHelper.DISEASE, DatabaseHelper.COST};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        ArrayList<PatientData> patientData =  new ArrayList<PatientData>();
        String searchName = searchPatientData.getPatientName();
        String searchDate = searchPatientData.getAppointmentDate();
        while (cursor.moveToNext()) {
            int ID = cursor.getInt(0);
            String PATIENT_NAME = cursor.getString(1);
            String TELEPHONE = cursor.getString(2);
            String EMAIL = cursor.getString(3);
            String TIME = cursor.getString(4);
            String DATE = cursor.getString(5);
            String MEDICINE = cursor.getString(6);
            String DISEASE = cursor.getString(7);
            String COST = cursor.getString(8);
            if (searchName != null){
                if(searchName.equals(PATIENT_NAME)){
                    patientData.add(new PatientData());
                    patientData.get(i).setID(ID);
                    patientData.get(i).setPatientName(PATIENT_NAME);
                    patientData.get(i).setEmail(EMAIL);
                    patientData.get(i).setAppointmentDate(DATE);
                    patientData.get(i).setTelephone(TELEPHONE);
                    patientData.get(i).setAppointmentTime(TIME);
                    patientData.get(i).setAppointmentDate(DATE);
                    patientData.get(i).setDisease(DISEASE);
                    patientData.get(i).setMedicine(MEDICINE);
                    patientData.get(i).setCost(COST);
                    i++;
                }
            }else if (searchDate != null ){
                if(searchDate.equals(DATE)){
                    patientData.add(new PatientData());
                    patientData.get(i).setID(ID);
                    patientData.get(i).setPatientName(PATIENT_NAME);
                    patientData.get(i).setEmail(EMAIL);
                    patientData.get(i).setAppointmentDate(DATE);
                    patientData.get(i).setTelephone(TELEPHONE);
                    patientData.get(i).setAppointmentTime(TIME);
                    patientData.get(i).setAppointmentDate(DATE);
                    patientData.get(i).setDisease(DISEASE);
                    patientData.get(i).setMedicine(MEDICINE);
                    patientData.get(i).setCost(COST);
                    i++;
                }
            }
        }
        return patientData;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private final static String DATABASE_NAME = "patienttabase";
        private final static int DATABASE_VERSION = 1;
        private final static String TABLE_NAME = "patienttable";
        private final static String UID = "_id";
        private final static String PATIENT_NAME = "patientname";
        private final static String TELEPHONE = "telephone";
        private final static String EMAIL = "email";
        private final static String DATE = "date";
        private final static String TIME = "time";
        private final static String MEDICINE = "medicine";
        private final static String DISEASE = "disease";
        private final static String COST = "cost";
        private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + PATIENT_NAME + " VARCHAR(255) ," + TELEPHONE + " VARCHAR(255) ," + EMAIL + " VARCHAR(255) ," + DATE + " VARCHAR(255) ," + TIME + " VARCHAR(255) ,"+ MEDICINE + " VARCHAR(255) ," + DISEASE + " VARCHAR(255) ,"+ COST + " VARCHAR);";
        private final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
                Toast.makeText(context, "database CREATED", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(context, "Sorry there is Error in create your database \n" + e, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
            } catch (SQLException e) {
                Toast.makeText(context, "Sorry there is Error in upgrade your database \n" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}