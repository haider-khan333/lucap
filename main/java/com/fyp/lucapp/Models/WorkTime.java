package com.fyp.lucapp.Models;

import java.io.Serializable;
import java.sql.Time;

public class WorkTime implements Serializable {

    private Time startTime;
    private Time endTime;

    public WorkTime(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
