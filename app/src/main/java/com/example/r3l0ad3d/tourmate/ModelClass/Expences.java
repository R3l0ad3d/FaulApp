package com.example.r3l0ad3d.tourmate.ModelClass;

/**
 * Created by r3l0ad3d on 5/4/17.
 */

public class Expences {
    private String userId;
    private String eventId;
    private String expenceResone;
    private String expenceAmount;
    private String expanceDate;
    private String spicalCode;

    public Expences(String expenceResone, String expenceAmount, String expanceDate) {
        this.expenceResone = expenceResone;
        this.expenceAmount = expenceAmount;
        this.expanceDate = expanceDate;
    }

    public Expences() {
    }

    public Expences(String userId, String eventId, String expenceResone,
                    String expenceAmount, String expanceDate, String spicalCode) {
        this.userId = userId;
        this.eventId = eventId;
        this.expenceResone = expenceResone;
        this.expenceAmount = expenceAmount;
        this.expanceDate = expanceDate;
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

    public String getExpenceResone() {
        return expenceResone;
    }

    public void setExpenceResone(String expenceResone) {
        this.expenceResone = expenceResone;
    }

    public String getExpenceAmount() {
        return expenceAmount;
    }

    public void setExpenceAmount(String expenceAmount) {
        this.expenceAmount = expenceAmount;
    }

    public String getExpanceDate() {
        return expanceDate;
    }

    public void setExpanceDate(String expanceDate) {
        this.expanceDate = expanceDate;
    }

    public String getSpicalCode() {
        return spicalCode;
    }

    public void setSpicalCode(String spicalCode) {
        this.spicalCode = spicalCode;
    }
}
