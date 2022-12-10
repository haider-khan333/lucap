package com.fyp.lucapp.Models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Reviews implements Serializable {

    private Date dateOfReview;
    private Time timeOfReview;
    private String reviewDescription;
    //this would be the id of the person who wrote the review
    private String reviewWriter;

    public Reviews(Date dateOfReview, Time timeOfReview,
                   String reviewDescription, String reviewWriter) {
        this.dateOfReview = dateOfReview;
        this.timeOfReview = timeOfReview;
        this.reviewDescription = reviewDescription;
        this.reviewWriter = reviewWriter;
    }

    public Date getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(Date dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public Time getTimeOfReview() {
        return timeOfReview;
    }

    public void setTimeOfReview(Time timeOfReview) {
        this.timeOfReview = timeOfReview;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public String getReviewWriter() {
        return reviewWriter;
    }

    public void setReviewWriter(String reviewWriter) {
        this.reviewWriter = reviewWriter;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "dateOfReview=" + dateOfReview +
                ", timeOfReview=" + timeOfReview +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", reviewWriter='" + reviewWriter + '\'' +
                '}';
    }
}
