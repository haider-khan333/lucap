package com.fyp.lucapp.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctors implements Serializable {
    private String _id;
    private String doctorName;
    private String doctorPassword;
    private String doctorContact;
    private String doctorImage;
    private WorkTime doctorWorkingHours;
    private Address doctorAddress;

    private ArrayList<String> doctorEmail;
    private ArrayList<String> doctorSpecialization;
    private ArrayList<Reviews> doctorReviews;

    private double doctorRating;
    private boolean doctorIsAvailable;


    public Doctors(String _id,
                   String doctorName, ArrayList<String> doctorEmail, String doctorPassword,
                   String doctorContact,
                   String doctorImage,
                   WorkTime doctorWorkingHours,
                   ArrayList<Reviews> doctorReviews,
                   ArrayList<String> doctorSpecialization,
                   double doctorRating,
                   boolean doctorIsAvailable,
                   Address doctorAddress) {
        this._id = _id;
        this.doctorName = doctorName;
        this.doctorPassword = doctorPassword;
        this.doctorContact = doctorContact;
        this.doctorImage = doctorImage;
        this.doctorWorkingHours = doctorWorkingHours;
        this.doctorReviews = doctorReviews;
        this.doctorEmail = doctorEmail;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorRating = doctorRating;
        this.doctorIsAvailable = doctorIsAvailable;
        this.doctorAddress = doctorAddress;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public String getDoctorContact() {
        return doctorContact;
    }

    public void setDoctorContact(String doctorContact) {
        this.doctorContact = doctorContact;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }

    public WorkTime getDoctorWorkingHours() {
        return doctorWorkingHours;
    }

    public void setDoctorWorkingHours(WorkTime doctorWorkingHours) {
        this.doctorWorkingHours = doctorWorkingHours;
    }

    public ArrayList<Reviews> getDoctorReviews() {
        return doctorReviews;
    }

    public void setDoctorReviews(ArrayList<Reviews> doctorReviews) {
        this.doctorReviews = doctorReviews;
    }

    public ArrayList<String> getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(ArrayList<String> doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public ArrayList<String> getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(ArrayList<String> doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public double getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(double doctorRating) {
        this.doctorRating = doctorRating;
    }

    public boolean isDoctorIsAvailable() {
        return doctorIsAvailable;
    }

    public void setDoctorIsAvailable(boolean doctorIsAvailable) {
        this.doctorIsAvailable = doctorIsAvailable;
    }

    public Address getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(Address doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "_id='" + _id + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPassword='" + doctorPassword + '\'' +
                ", doctorContact='" + doctorContact + '\'' +
                ", doctorImage='" + doctorImage + '\'' +
                ", doctorWorkingHours=" + doctorWorkingHours +
                ", doctorReviews=" + doctorReviews +
                ", doctorEmail=" + doctorEmail +
                ", doctorSpecialization=" + doctorSpecialization +
                ", doctorRating=" + doctorRating +
                ", doctorIsAvailable=" + doctorIsAvailable +
                ", doctorAddress=" + doctorAddress +
                '}';
    }
}
