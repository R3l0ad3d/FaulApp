package com.example.r3l0ad3d.tourmate.ModelClass;

/**
 * Created by r3l0ad3d on 5/2/17.
 */

public class ShareMoment {
    private String momentId;
    private String userId;
    private String eventId;
    private String userName;
    private String description;
    private String imageURL;
    private boolean isShareAble;
    private String specialCode;

    public ShareMoment(String userId, String description, String imageURL) {
        this.userId = userId;
        this.description = description;
        this.imageURL = imageURL;
    }

    public ShareMoment(String momentId, String userId, String eventId,
                       String userName, String description, String imageURL,
                       boolean isShareAble, String specialCode) {
        this.momentId = momentId;
        this.userId = userId;
        this.eventId = eventId;
        this.userName = userName;
        this.description = description;
        this.imageURL = imageURL;
        this.isShareAble = isShareAble;
        this.specialCode = specialCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isShareAble() {
        return isShareAble;
    }

    public void setShareAble(boolean shareAble) {
        isShareAble = shareAble;
    }

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }
}
