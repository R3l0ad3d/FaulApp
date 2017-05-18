package com.example.r3l0ad3d.tourmate.ModelClass;

/**
 * Created by r3l0ad3d on 5/2/17.
 */

public class UserInformation {
    private String userID;
    private String userName;
    private String passWord;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String eMail;
    private String mobileNo;
    private String area;
    private String imageURL;
    private String specialCode;

    public UserInformation() {
    }

    public UserInformation(String passWord, String fullName, String eMail) {
        this.passWord = passWord;
        this.fullName = fullName;
        this.eMail = eMail;
    }

    public UserInformation(String userID, String userName, String passWord,
                           String fullName, String gender, String dateOfBirth,
                           String eMail, String mobileNo, String area, String imageURL,
                           String specialCode) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
        this.mobileNo = mobileNo;
        this.area = area;
        this.imageURL = imageURL;
        this.specialCode = specialCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }
}
