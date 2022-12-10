package com.fyp.lucapp.Models;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Medications implements Serializable {
    private String medicineName;
    private String medicineGrams;
    private Date startDate, endDate;
    private ArrayList<Time> medicineTime;
    private Time medicineGeneratedTime;

    public Medications(String medicineName, String medicineGrams,
                       Date startDate,Date endDate,
                       ArrayList<Time> medicineTime
                       ,Time medicineGeneratedTime) {
        this.medicineName = medicineName;
        this.medicineGrams = medicineGrams;
        this.medicineTime = medicineTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicineGeneratedTime = medicineGeneratedTime;

    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineGrams() {
        return medicineGrams;
    }

    public void setMedicineGrams(String medicineGrams) {
        this.medicineGrams = medicineGrams;
    }

    public ArrayList<Time> getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(ArrayList<Time> medicineTime) {
        this.medicineTime = medicineTime;
    }

    public Time getMedicineGeneratedTime() {
        return medicineGeneratedTime;
    }

    public void setMedicineGeneratedTime(Time medicineGeneratedTime) {
        this.medicineGeneratedTime = medicineGeneratedTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Medications{" +
                "medicineName='" + medicineName + '\'' +
                ", medicineGrams='" + medicineGrams + '\'' +
                ", medicineTime=" + medicineTime.toString() +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", medicineGeneratedTime=" + medicineGeneratedTime.toString() +
                '}';
    }
}
