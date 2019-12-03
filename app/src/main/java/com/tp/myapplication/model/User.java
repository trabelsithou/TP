package com.tp.myapplication.model;

public class User {
    String fullName;
    String email;
    String type;
    String image;
    String password;
    boolean present;

    public User(String password, String fullName, String email, String type, String image, boolean present) {
        this.fullName = fullName;
        this.email = email;
        this.type = type;
        this.image = image;
        this.present = present;
        this.password = password;
    }

    public User(String fullName, boolean status) {
        this.fullName = fullName;

        this.present = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
