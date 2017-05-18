package com.example.r3l0ad3d.tourmate.ModelClass;

import java.io.Serializable;

/**
 * Created by r3l0ad3d on 5/4/17.
 */

public class Event implements Serializable{
    private String userId;
    private String eventId;
    private String eventCreateDate;
    private String eventPlace;
    private String toDate;
    private String fromDate;
    private String estimatedBudget;
    private String spicalCode;

    public Event() {
    }

    public Event(String eventCreateDate,
                 String eventPlace, String toDate, String fromDate,
                 String estimatedBudget) {
        this.eventCreateDate = eventCreateDate;
        this.eventPlace = eventPlace;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.estimatedBudget = estimatedBudget;
    }

    public Event(String userId, String eventId,String eventCreateDate, String eventPlace,
                 String toDate,String fromDate, String estimatedBudget, String spicalCode) {
        this.userId = userId;
        this.eventId = eventId;
        this.eventCreateDate = eventCreateDate;
        this.eventPlace = eventPlace;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.estimatedBudget = estimatedBudget;
        this.spicalCode = spicalCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventCreateDate() {
        return eventCreateDate;
    }

    public void setEventCreateDate(String eventCreateDate) {
        this.eventCreateDate = eventCreateDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getEstimatedBudget() {
        return estimatedBudget;
    }

    public void setEstimatedBudget(String estimatedBudget) {
        this.estimatedBudget = estimatedBudget;
    }

    public String getSpicalCode() {
        return spicalCode;
    }

    public void setSpicalCode(String spicalCode) {
        this.spicalCode = spicalCode;
    }
}
