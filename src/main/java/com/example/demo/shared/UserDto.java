package com.example.demo.shared;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String userName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;

    public void setUserName(String firstName) {
        this.userName = firstName;
    }

    public String getUserName() {
        return userName;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }



}
