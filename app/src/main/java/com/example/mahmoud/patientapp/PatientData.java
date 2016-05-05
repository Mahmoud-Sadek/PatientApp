package com.example.mahmoud.patientapp;

/**
 * Created by Mahmoud on 5/2/2016.
 */
public class PatientData {
    public int getID() {
        return ID;
    }

    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    private String PatientName;
    private String Telephone;
    private String Email;
    private String AppointmentTime;
    private String AppointmentDate;
    private String medicine;
    private String Disease;
    private String Cost;

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getPatientName() {
        return PatientName;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getDisease() {
        return Disease;
    }

    public String getCost() {
        return Cost;
    }
}
