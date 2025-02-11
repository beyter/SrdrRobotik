package com.srdr.srdrrobotik.model;

public class UserAuthInfo {
    private String email;
    private String password;

    public UserAuthInfo(String email) {
        this.email = email;
    }

    public UserAuthInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
