package com.accenture.operations.shared;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String name;
    private String surname;
    private String email;
    private int status;

    public User () {
        this.userName = "Susantez";
        this.name = "Serkan";
        this.surname = "Susantez";
        this.email = "serkan.susantez@accenture.com";
        this.status = 1;
    }

    public User (String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.status = 1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
