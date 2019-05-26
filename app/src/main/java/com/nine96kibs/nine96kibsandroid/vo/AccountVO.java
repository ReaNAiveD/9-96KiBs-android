package com.nine96kibs.nine96kibsandroid.vo;

import java.io.Serializable;

public class AccountVO implements Serializable {

    private String username;
    private String password;

    public AccountVO() {
    }

    public AccountVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
