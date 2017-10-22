package com.only.yc.only_sqlite;

/**
 * Created by yc on 2017/10/19.
 */

public class Person {
    private String UserID;//用户名；
    private String UserName;//昵称；
    private String UserPassword;//密码
    private String UserE_mall;//邮箱
    private String UserAutograph;//个性签名
    private String UserImage;//用户头像
    private String UserSex;//用户性别；
    private String UserBirthday;//用户生日

    public Person(String userID, String userName, String userPassword, String userE_mall,
                  String userSex, String userBirthday, String userImage, String userAutograph) {
        UserID = userID;
        UserName = userName;
        UserPassword = userPassword;
        UserE_mall = userE_mall;
        UserAutograph = userAutograph;
        UserImage = userImage;
        UserSex = userSex;
        UserBirthday = userBirthday;
    }

    public Person(String userName, String userAutograph) {
        UserName = userName;
        UserAutograph = userAutograph;
    }

    @Override
    public String toString() {
        return "UserID = "+this.UserID+",UserName = " + this.UserName+",UserPassword = " +this.UserPassword+
                ",UserE_mall = " + this.UserE_mall +",UserSex = "+UserSex+",UserBirthday = "+this.UserBirthday+
                ",userImage = " +this.UserImage+",UserAutograph = "+this.UserAutograph;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserE_mall() {
        return UserE_mall;
    }

    public void setUserE_mall(String userE_mall) {
        UserE_mall = userE_mall;
    }

    public String getUserAutograph() {
        return UserAutograph;
    }

    public void setUserAutograph(String userAutograph) {
        UserAutograph = userAutograph;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getUserBirthday() {
        return UserBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        UserBirthday = userBirthday;
    }
}
